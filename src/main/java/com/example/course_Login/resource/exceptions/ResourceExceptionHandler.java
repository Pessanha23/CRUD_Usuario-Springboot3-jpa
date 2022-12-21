package com.example.course_Login.resource.exceptions;

import com.example.course_Login.service.exceptions.EmailIgualException;
import com.example.course_Login.service.exceptions.EmailNaoEncontrado;
import com.example.course_Login.service.exceptions.SenhaDiferente;
import com.example.course_Login.service.exceptions.SenhaExistente;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EmailIgualException.class)
    public ResponseEntity<StandardError> resourceNotFound(EmailIgualException e, HttpServletRequest request){
        String error = "RESOURCE NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(), error, e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EmailNaoEncontrado.class)
    public ResponseEntity<StandardError> resourceNotFound(EmailNaoEncontrado e, HttpServletRequest request){
        String error = "RESOURCE NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(), error, e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(SenhaDiferente.class)
    public ResponseEntity<StandardError> resourceNotFound(SenhaDiferente e, HttpServletRequest request){
        String error = "RESOURCE NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(), error, e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(SenhaExistente.class)
    public ResponseEntity<StandardError> resourceNotFound(SenhaExistente e, HttpServletRequest request){
        String error = "RESOURCE NOT FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(), error, e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

}
