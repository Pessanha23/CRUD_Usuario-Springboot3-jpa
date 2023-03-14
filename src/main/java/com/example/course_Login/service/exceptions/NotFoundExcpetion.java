package com.example.course_Login.service.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundExcpetion extends PaiException{
    public NotFoundExcpetion(String message, String error) {
        super(message, error, HttpStatus.NOT_FOUND);
    }
}
