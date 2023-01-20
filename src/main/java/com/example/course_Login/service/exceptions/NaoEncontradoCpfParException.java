package com.example.course_Login.service.exceptions;

public class NaoEncontradoCpfParException extends RuntimeException {

    public NaoEncontradoCpfParException(Object telefoneSet) {
        super("CPF PAR NÃO ENCONTRADO NA LISTA");
    }
}
