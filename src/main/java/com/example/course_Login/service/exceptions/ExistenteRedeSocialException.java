package com.example.course_Login.service.exceptions;

public class ExistenteRedeSocialException extends NotAcceptableException{

    public ExistenteRedeSocialException(Object redeSocialSet){
        super("MIDIA EXISTENTE!! INSIRA OUTRO TIPO DE MIDIA OU ALTERE A CADASTRADA", "EXISTING REDE SOCIAL");
    }
}
