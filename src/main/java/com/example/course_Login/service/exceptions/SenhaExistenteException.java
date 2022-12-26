package com.example.course_Login.service.exceptions;

public class SenhaExistenteException extends RuntimeException{

    public SenhaExistenteException(Object senha){
        super("SENHA EXISTENTE!! MODIFIQUE SUA SENHA");
    }
}
