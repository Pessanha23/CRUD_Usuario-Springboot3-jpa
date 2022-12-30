package com.example.course_Login.service.exceptions;

public class EmailExistenteException extends RuntimeException{

    public EmailExistenteException(Object email){
        super("EMAIL EXISTENTE!!");
    }
}
