package com.example.course_Login.service.exceptions;

public class NaoEncontradoCpfParException extends NotFoundExcpetion {

    public NaoEncontradoCpfParException(Object telefoneSet) {
        super("CPF PAR NÃO ENCONTRADO NA LISTA","CPF NOT FOUND");
    }
}
