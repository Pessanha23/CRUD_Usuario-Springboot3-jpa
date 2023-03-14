package com.example.course_Login.service.exceptions;

public class ExistenteSenhaException extends NotAcceptableException{

    public ExistenteSenhaException(Object senha){
        super("SENHA EXISTENTE!! MODIFIQUE SUA SENHA", "EXISTING PASSWORD");
    }
}
