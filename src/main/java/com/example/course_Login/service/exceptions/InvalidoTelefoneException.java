package com.example.course_Login.service.exceptions;

public class InvalidoTelefoneException extends BadRequestException {

    public InvalidoTelefoneException(Object telefoneSet){
        super("TELEFONE INVÁLIDO","INVALID TELEFONE");
    }
}
