package com.example.course_Login.service.exceptions;

public class ExistenteCpfException extends NotAcceptableException {
    public ExistenteCpfException(Object cpf){
        super("CPF REGISTRADO!! INFORME OUTRO CPF", "EXISTING CPF");
    }
}
