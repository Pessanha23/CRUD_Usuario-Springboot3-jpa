package com.example.course_Login.service.exceptions;

public class ExistenteEmailException extends RuntimeException{

    public ExistenteEmailException(Object email){
        super("EMAIL EXISTENTE!!");
    }
}
