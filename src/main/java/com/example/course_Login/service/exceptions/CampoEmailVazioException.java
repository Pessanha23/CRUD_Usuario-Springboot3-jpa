package com.example.course_Login.service.exceptions;

public class CampoEmailVazioException extends RuntimeException {

    public CampoEmailVazioException(Object email) {
        super("CAMPO EMAIL VAZIO");
    }
}
