package com.example.course_Login.service.exceptions;

import org.springframework.http.HttpStatus;

public class PaiException extends RuntimeException {
    String error;
    HttpStatus httpStatus;

    public PaiException(String message, String error, HttpStatus httpStatus) {
        super(message);
        this.error = error;
        this.httpStatus = httpStatus;
    }

    public String getError() {
        return error;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
