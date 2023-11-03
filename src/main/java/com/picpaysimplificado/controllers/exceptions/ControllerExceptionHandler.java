package com.picpaysimplificado.controllers.exceptions;

import com.picpaysimplificado.services.exceptions.BusinessRuleException;
import com.picpaysimplificado.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<StandardError> BusinessRule(BusinessRuleException ex, HttpServletRequest req){
        String error = "Regra de negócio error.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, ex.getMessage(), req.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> NotFound(ObjectNotFoundException ex, HttpServletRequest req){
        String error = "Objeto não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        com.picpaysimplificado.controllers.exceptions.StandardError standardError = new com.picpaysimplificado.controllers.exceptions.StandardError(Instant.now(), status.value(), error, ex.getMessage(), req.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }
}
