package com.example.course_Login.service.exceptions;

public class EmailNaoEncontrado extends RuntimeException {

    public EmailNaoEncontrado(Object email) {
        super("EMAIL NÃO ENCONTRADO" + email);
    }
}
