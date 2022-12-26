package com.example.course_Login.service.exceptions;

public class EmailNaoEncontradoException extends RuntimeException {

    public EmailNaoEncontradoException(Object email) {
        super("EMAIL NÃO ENCONTRADO" + email);
    }
}
