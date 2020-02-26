package com.godeltech.pt11.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cars not found")
public class CarsNotFoundException extends RuntimeException {

    public CarsNotFoundException() {
        super("Cars not found");
    }
}
