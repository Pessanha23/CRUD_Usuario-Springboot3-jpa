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
    @ExceptionHandler(value = {PaiException.class})
    public ResponseEntity<StandardError> paiExcpetion(PaiException e, HttpServletRequest request) {
        String error = e.getError();
        HttpStatus status = e.getHttpStatus();
        StandardError err = new StandardError(Instant.now(), status.value(),
                error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
