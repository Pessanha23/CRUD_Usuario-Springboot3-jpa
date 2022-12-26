package com.example.course_Login.resource.exceptions;

import com.example.course_Login.service.exceptions.EmailIgualException;
import com.example.course_Login.service.exceptions.IdNaoEncontradoException;
import com.example.course_Login.service.exceptions.SenhaDiferenteException;
import com.example.course_Login.service.exceptions.SenhaExistenteException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EmailIgualException.class)
    public ResponseEntity<StandardError> emailIgual(EmailIgualException e, HttpServletRequest request){
        String error = "EMAIL EXISTING";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(), error, e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(IdNaoEncontradoException.class)
    public ResponseEntity<StandardError> idNaoEncontrado(IdNaoEncontradoException e, HttpServletRequest request){
        String error = "ID NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(), error, e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(SenhaDiferenteException.class)
    public ResponseEntity<StandardError> senhaDiferente(SenhaDiferenteException e, HttpServletRequest request){
        String error = "DIFFERENT PASSWORD";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(), error, e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(SenhaExistenteException.class)
    public ResponseEntity<StandardError> senhaExistente(SenhaExistenteException e, HttpServletRequest request){
        String error = "EXISTING PASSWORD";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(), error, e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

}
