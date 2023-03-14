package com.example.course_Login.service.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends PaiException{
    public BadRequestException(String message, String error) {
        super(message, error, HttpStatus.BAD_REQUEST);
    }
}
