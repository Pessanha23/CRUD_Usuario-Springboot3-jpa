package com.example.course_Login.service.exceptions;

public class SenhaExistente extends RuntimeException{

    public SenhaExistente(Object senha){
        super("SENHA EXISTENTE!! MODIFIQUE SUA SENHA");
    }
}
