package com.example.course_Login.service.exceptions;

public class ExistenteEmailException extends NotAcceptableException{

    public ExistenteEmailException(Object email){
        super("EMAIL EXISTENTE!!", "EXISTING EMAIL");
    }
}
