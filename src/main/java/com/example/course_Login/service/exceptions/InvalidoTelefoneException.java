package com.example.course_Login.service.exceptions;

public class InvalidoTelefoneException extends RuntimeException {

    public InvalidoTelefoneException(Object telefoneSet){
        super("TELEFONE INVÁLIDO");
    }
}
