package com.example.course_Login.service.exceptions;

public class CampoCpfVazioException extends BadRequestException{
    public CampoCpfVazioException (Object cpf){
        super("CAMPO CPF VAZIO", "CPF IS NULL");

    }
}
