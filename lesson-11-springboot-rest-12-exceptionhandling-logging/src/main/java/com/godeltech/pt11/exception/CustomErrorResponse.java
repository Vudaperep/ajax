package com.godeltech.pt11.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class CustomErrorResponse {

    private LocalDateTime localDateTime;
    private HttpStatus Status;
    private String error;

    public void setTime(LocalDateTime now) {
    }

    public void setMessage(String message) {
    }
}
