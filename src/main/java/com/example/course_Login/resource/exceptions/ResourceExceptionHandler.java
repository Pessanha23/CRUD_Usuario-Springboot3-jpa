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
    public ResponseEntity<StandardError> passwordExistingException(ExistenteSenhaException e, HttpServletRequest request) {
        String error = "EXISTING PASSWORD";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(value = {ExistenteEmailException.class})
    public ResponseEntity<StandardError> emailExistingException(ExistenteEmailException e, HttpServletRequest request) {
        String error = "EXISTING EMAIL";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(value = {ExistenteCpfException.class})
    public ResponseEntity<StandardError> cpfExistingException(ExistenteCpfException e, HttpServletRequest request) {
        String error = "EXISTING CPF";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    //ler novamente site adicionado nos favoritos

    @ExceptionHandler(value = {NaoEncontradoIdException.class})
    public ResponseEntity<StandardError> idNotFoundException(NaoEncontradoIdException e, HttpServletRequest request) {
        String error = "ID NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(value = {NaoEncontradoEmailException.class})
    public ResponseEntity<StandardError> emailNotFoundException(NaoEncontradoEmailException e, HttpServletRequest request) {
        String error = "EMAIL NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(value = {NaoEncontradoCpfException.class})
    public ResponseEntity<StandardError> cpfNotFoundException(NaoEncontradoCpfException e, HttpServletRequest request) {
        String error = "CPF NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(value = {NaoEncontradoTelefoneException.class})
    public ResponseEntity<StandardError> telefoneNotFoundException(NaoEncontradoTelefoneException e, HttpServletRequest request) {
        String error = "TELEFONE NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(value = {NaoEncontradoCpfParException.class})
    public ResponseEntity<StandardError> cpfParNotFoundException(NaoEncontradoCpfParException e, HttpServletRequest request) {
        String error = "CPF NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler({InvalidoSenhaException.class})
    public ResponseEntity<StandardError> passwordInvalid(RuntimeException e, HttpServletRequest request) {
        String error = "DIFFERENT PASSWORD";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler({InvalidoCpfException.class})
    public ResponseEntity<StandardError> cpfInvalid(InvalidoCpfException e, HttpServletRequest request) {
        String error = "INVALID CPF";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(value = {CampoEmailVazioException.class})
    public ResponseEntity<StandardError> emailEmptyFieldException(RuntimeException e, HttpServletRequest request) {
        String error = "EMAIL IS NULL";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(value = {CampoSenhaVazioException.class})
    public ResponseEntity<StandardError> passwordEmptyFieldException(CampoSenhaVazioException e, HttpServletRequest request) {
        String error = "PASSWORD IS NULL";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(value = {CampoCpfVazioException.class})
    public ResponseEntity<StandardError> cpfEmptyFieldException(CampoCpfVazioException e, HttpServletRequest request) {
        String error = "CPF IS NULL";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
