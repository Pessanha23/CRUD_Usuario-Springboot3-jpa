package com.example.course_Login.service.exceptions;

public class IdNaoEncontradoException extends RuntimeException {

    public IdNaoEncontradoException(Object id) {
        super("ID NÃO ENCONTRADO" + id);
    }
}
