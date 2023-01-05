package com.example.course_Login.service.exceptions;

public class NaoEncontradoIdException extends RuntimeException {

    public NaoEncontradoIdException(Object id) {
        super("ID NÃO ENCONTRADO: " + id);
    }
}
