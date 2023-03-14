package com.example.course_Login.service.exceptions;

public class CampoSenhaVazioException extends BadRequestException{
    public CampoSenhaVazioException(Object senha){
        super("CAMPO SENHA VAZIO","PASSWORD IS NULL");
    }
}
