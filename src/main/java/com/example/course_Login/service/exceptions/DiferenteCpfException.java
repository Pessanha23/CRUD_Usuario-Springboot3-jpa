package com.example.course_Login.service.exceptions;

public class DiferenteCpfException extends RuntimeException{
    public DiferenteCpfException(Object cpf){
        super("Quantidade de numeros diferente do padrão");
    }
}
