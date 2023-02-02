package com.example.course_Login.service.exceptions;

public class ExistenteRedeSocialException extends RuntimeException{

    public ExistenteRedeSocialException(Object redeSocialSet){
        super("MIDIA EXISTENTE!! INSIRA OUTRO TIPO DE MIDIA OU ALTERE A CADASTRADA");
    }
}
