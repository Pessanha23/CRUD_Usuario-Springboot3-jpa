package com.example.course_Login.service.exceptions;

public class NaoEncontradoCpfException extends RuntimeException{

    public NaoEncontradoCpfException(Object cpf){
        super("CPF NÃO ENCONTRADO: " + cpf);
    }
}
