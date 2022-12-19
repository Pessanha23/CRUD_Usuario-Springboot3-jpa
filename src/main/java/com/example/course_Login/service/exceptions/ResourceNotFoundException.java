package com.example.course_Login.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Resource not found.ID" + id);
    }
}
