package com.example.course_Login.service.exceptions;

public class SenhaDiferente extends RuntimeException {

    public SenhaDiferente(Object senha){
        super("SENHAS DIFERENTES! CADASTRE NOVAMENTE");
    }
}
