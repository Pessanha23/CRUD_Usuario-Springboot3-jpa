package com.example.course_Login.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object email) {
        super("Resource not found.ID" + email);
    }
}
