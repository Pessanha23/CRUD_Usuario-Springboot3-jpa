package com.example.course_Login.service.exceptions;

public class ExistenteSenhaException extends RuntimeException{

    public ExistenteSenhaException(Object senha){
        super("SENHA EXISTENTE!! MODIFIQUE SUA SENHA");
    }
}
