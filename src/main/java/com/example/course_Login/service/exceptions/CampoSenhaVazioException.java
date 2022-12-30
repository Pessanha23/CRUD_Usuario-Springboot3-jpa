package com.example.course_Login.service.exceptions;

public class CampoSenhaVazioException extends RuntimeException{
    public CampoSenhaVazioException(Object senha){
        super("CAMPO SENHA VAZIO");
    }
}
