package com.example.course_Login.service.exceptions;

public class ExistenteTelefoneException extends NotAcceptableException{

    public ExistenteTelefoneException(Object telefoneSet){
        super("TELEFONE EXISTENTE!! MODIFIQUE O TELEFONE", "EXISTING TELEFONE");
    }
}
