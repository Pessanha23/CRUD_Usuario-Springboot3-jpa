package com.example.course_Login.service.exceptions;

public class DiferenteSenhaException extends RuntimeException {

    public DiferenteSenhaException(Object senha){
        super("SENHAS DIFERENTES! CADASTRE NOVAMENTE");
    }
}
