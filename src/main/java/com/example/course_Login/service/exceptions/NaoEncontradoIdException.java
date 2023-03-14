package com.example.course_Login.service.exceptions;

public class NaoEncontradoIdException extends NotFoundExcpetion {

    public NaoEncontradoIdException(Object id) {
        super("ID NÃO ENCONTRADO NA LISTA: " + id, "ID NOT FOUND");
    }
}
