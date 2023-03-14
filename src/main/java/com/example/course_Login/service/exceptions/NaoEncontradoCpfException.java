package com.example.course_Login.service.exceptions;

public class NaoEncontradoCpfException extends NotFoundExcpetion{

    public NaoEncontradoCpfException(Object cpf){
        super("CPF NÃO ENCONTRADO: " + cpf,"CPF NOT FOUND");
    }
}
