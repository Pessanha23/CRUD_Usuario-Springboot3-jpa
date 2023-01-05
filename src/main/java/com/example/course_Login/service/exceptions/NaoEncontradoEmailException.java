package com.example.course_Login.service.exceptions;

public class NaoEncontradoEmailException extends RuntimeException {

    public NaoEncontradoEmailException(Object email) {
        super("EMAIL NÃO ENCONTRADO: " + email);
    }
}
