package com.example.course_Login.service.exceptions;

public class InvalidoSenhaException extends RuntimeException {

    public InvalidoSenhaException(Object senha){
        super("SENHA INVÁLIDA");
    }
}
