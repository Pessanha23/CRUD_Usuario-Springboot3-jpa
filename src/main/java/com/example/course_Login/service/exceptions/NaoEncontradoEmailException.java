package com.example.course_Login.service.exceptions;

public class NaoEncontradoEmailException extends NotFoundException {

    public NaoEncontradoEmailException(Object email) {
        super("EMAIL NÃO ENCONTRADO: " + email, "EMAIL NOT FOUND");
    }
}
