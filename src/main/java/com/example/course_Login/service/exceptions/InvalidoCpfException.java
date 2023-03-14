package com.example.course_Login.service.exceptions;

public class InvalidoCpfException extends BadRequestException{
    public InvalidoCpfException(Object cpf){
        super("CPF INVÁLIDO", "INVALID CPF");
    }
}
