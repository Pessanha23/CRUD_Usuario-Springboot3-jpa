package com.example.course_Login.service.exceptions;

public class InvalidoCpfException extends RuntimeException{
    public InvalidoCpfException(Object cpf){
        super("CPF INVÁLIDO");
    }
}
