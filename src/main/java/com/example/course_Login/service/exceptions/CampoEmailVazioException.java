package com.example.course_Login.service.exceptions;

public class CampoEmailVazioException extends BadRequestException {

    public CampoEmailVazioException(Object email) {
        super("CAMPO EMAIL VAZIO","EMAIL IS NULL");
    }
}
