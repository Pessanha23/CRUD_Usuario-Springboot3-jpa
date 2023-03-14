package com.example.course_Login.service.exceptions;

import org.springframework.http.HttpStatus;

public class NotAcceptableException extends PaiException{
    public NotAcceptableException(String message, String error) {
        super(message, error, HttpStatus.NOT_ACCEPTABLE);
    }
}
