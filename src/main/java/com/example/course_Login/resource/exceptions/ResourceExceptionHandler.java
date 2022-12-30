package com.example.course_Login.resource.exceptions;

import com.example.course_Login.service.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(value = {SenhaExistenteException.class, EmailExistenteException.class})
    public ResponseEntity<StandardError> existingException(RuntimeException e, HttpServletRequest request) {
        String error = "EXISTING";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(value = {IdNaoEncontradoException.class, EmailNaoEncontradoException.class })
    public ResponseEntity<StandardError> notFoundException(RuntimeException e, HttpServletRequest request) {
        String error = "NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(SenhaDiferenteException.class)
    public ResponseEntity<StandardError> senhaDiferente(SenhaDiferenteException e, HttpServletRequest request) {
        String error = "DIFFERENT PASSWORD";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(value = {CampoEmailVazioException.class, CampoSenhaVazioException.class})
    public ResponseEntity<StandardError> emptyFieldException(RuntimeException e, HttpServletRequest request) {
        String error = "EMPTY FIELD";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
