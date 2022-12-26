package com.example.course_Login.service.exceptions;

public class SenhaDiferenteException extends RuntimeException {

    public SenhaDiferenteException(Object senha){
        super("SENHAS DIFERENTES! CADASTRE NOVAMENTE");
    }
}
