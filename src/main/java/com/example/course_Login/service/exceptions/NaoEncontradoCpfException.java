package com.example.course_Login.service.exceptions;

public class NaoEncontradoCpfException extends NotFoundException {

    public NaoEncontradoCpfException(Object cpf){
        super("CPF NÃO ENCONTRADO: " + cpf,"CPF NOT FOUND");
    }
}
