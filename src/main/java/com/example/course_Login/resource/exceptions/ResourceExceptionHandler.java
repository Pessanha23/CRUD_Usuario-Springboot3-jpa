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

    @ExceptionHandler(value = {ExistenteSenhaException.class})
    public ResponseEntity<StandardError> existingException(ExistenteSenhaException e, HttpServletRequest request) {
        String error = "EXISTING PASSWORD";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(value = {ExistenteEmailException.class})
    public ResponseEntity<StandardError> existingException(ExistenteEmailException e, HttpServletRequest request) {
        String error = "EXISTING EMAIL";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(value = {ExistenteCpfException.class})
    public ResponseEntity<StandardError> existingException(ExistenteCpfException e, HttpServletRequest request) {
        String error = "EXISTING CPF";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    //ler novamente site adicionado nos favoritos

    @ExceptionHandler(value = {NaoEncontradoIdException.class})
    public ResponseEntity<StandardError> notFoundException(NaoEncontradoIdException e, HttpServletRequest request) {
        String error = "ID NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(value = {NaoEncontradoEmailException.class})
    public ResponseEntity<StandardError> notFoundException(NaoEncontradoEmailException e, HttpServletRequest request) {
        String error = "EMAIL NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(value = {NaoEncontradoCpfException.class})
    public ResponseEntity<StandardError> notFoundException(NaoEncontradoCpfException e, HttpServletRequest request) {
        String error = "CPF NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler({DiferenteSenhaException.class})
    public ResponseEntity<StandardError> passwordDiferente(RuntimeException e, HttpServletRequest request) {
        String error = "DIFFERENT PASSWORD";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler({DiferenteCpfException.class})
    public ResponseEntity<StandardError> passwordDiferente(DiferenteCpfException e, HttpServletRequest request) {
        String error = "DIFFERENT CPF";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(value = {CampoEmailVazioException.class})
    public ResponseEntity<StandardError> emptyFieldException(RuntimeException e, HttpServletRequest request) {
        String error = "EMAIL IS NULL";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(value = {CampoSenhaVazioException.class})
    public ResponseEntity<StandardError> emptyFieldException(CampoSenhaVazioException e, HttpServletRequest request) {
        String error = "PASSWORD IS NULL";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(value = {CampoCpfVazioException.class})
    public ResponseEntity<StandardError> emptyFieldException(CampoCpfVazioException e, HttpServletRequest request) {
        String error = "CPF IS NULL";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
