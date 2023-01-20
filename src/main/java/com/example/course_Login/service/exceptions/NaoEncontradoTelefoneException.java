package com.example.course_Login.service.exceptions;

public class NaoEncontradoTelefoneException extends RuntimeException {

    public NaoEncontradoTelefoneException(Object telefoneSet) {
        super("TELEFONE NÃO ENCONTRADO NA LISTA");
    }
}
