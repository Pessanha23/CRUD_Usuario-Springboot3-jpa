package com.example.course_Login.service.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends PaiException{
    public NotFoundException(String message, String error) {
        super(message, error, HttpStatus.NOT_FOUND);
    }
}
