package com.example.course_Login.service.exceptions;

public class ExistenteTelefoneException extends RuntimeException{

    public ExistenteTelefoneException(Object telefoneSet){
        super("TELEFONE EXISTENTE!! MODIFIQUE O TELEFONE");
    }
}
