package com.example.course_Login.service.exceptions;

public class NaoEncontradoTelefoneException extends NotFoundExcpetion {

    public NaoEncontradoTelefoneException(Object telefoneSet) {
        super("TELEFONE NÃO ENCONTRADO NA LISTA", "TELEFONE NOT FOUND");
    }
}
