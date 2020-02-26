package com.godeltech.pt11.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    CustomErrorResponse errors;
    @ExceptionHandler(value = CarNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> carNotFound(CarNotFoundException ex) {

        errors = new CustomErrorResponse();
        errors.setLocalDateTime(LocalDateTime.now(ZoneId.of("Europe/Minsk")));
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND);
        logging(ex);

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CarsNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> carsNotFound(Exception ex) {
        errors = new CustomErrorResponse();
        errors.setLocalDateTime(LocalDateTime.now(ZoneId.of("Europe/Minsk")));
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND);
        logging(ex);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ColourNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> colourNotFound(Exception ex) {
        errors = new CustomErrorResponse();
        errors.setLocalDateTime(LocalDateTime.now(ZoneId.of("Europe/Minsk")));
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND);
        logging(ex);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> validateFailed(Exception ex) {
        errors = new CustomErrorResponse();
        errors.setLocalDateTime(LocalDateTime.now(ZoneId.of("Europe/Minsk")));
        errors.setError( "Invalid data supplied!");
        errors.setStatus(HttpStatus.BAD_REQUEST);
        logging(ex);
        return ResponseEntity.status(errors.getStatus()).body(errors);
    }

    private void logging(Exception ex) {
        log.info("Request: {}", ex.getLocalizedMessage());
    }
}
