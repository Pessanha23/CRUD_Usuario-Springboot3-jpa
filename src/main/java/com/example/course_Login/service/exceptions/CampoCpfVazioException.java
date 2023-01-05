package com.example.course_Login.service.exceptions;

public class CampoCpfVazioException extends RuntimeException{
    public CampoCpfVazioException (Object cpf){
        super("CAMPO CPF VAZIO");
    }
}
