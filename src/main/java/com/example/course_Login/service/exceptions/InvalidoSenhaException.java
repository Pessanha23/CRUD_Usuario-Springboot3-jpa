package com.example.course_Login.service.exceptions;

public class InvalidoSenhaException extends BadRequestException {

    public InvalidoSenhaException(Object senha){
        super("SENHA INVÁLIDA", "DIFFERENT PASSWORD");
    }
}
