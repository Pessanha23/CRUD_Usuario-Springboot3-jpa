package com.example.course_Login.service.exceptions;

public class EmailIgualException extends RuntimeException{

    public EmailIgualException(Object email){
        super("EMAIL JÁ EXISTENTE!!");
    }
}
