package com.godeltech.pt11.exception;

import com.godeltech.pt11.entity.Colour;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Colour not found")
public class ColourNotFoundException extends RuntimeException {

    public ColourNotFoundException(Colour colour) {
        super("Car with this colour:" + colour + " not found");
    }
}
