package com.dylan.projet.ApiDemo.exceptions.handlers;

import com.dylan.projet.ApiDemo.exceptions.ObjectValidatorException;
import com.dylan.projet.ApiDemo.exceptions.OperationNotPermittedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectValidatorException.class)
    public ResponseEntity<ExceptionResponsePattern> handleObjectValidatorException(ObjectValidatorException e) {
        ExceptionResponsePattern response = ExceptionResponsePattern.builder()
                .message("Validation error")
                .source(e.getSource())
                .validationErrors(e.getViolations())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponsePattern> handleEntityNotFoundException(EntityNotFoundException e) {
        ExceptionResponsePattern response = ExceptionResponsePattern.builder()
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ExceptionResponsePattern> handleOperationNotPermittedException(OperationNotPermittedException e) {
        ExceptionResponsePattern response = ExceptionResponsePattern.builder()
                .message(e.getErrorMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponsePattern> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ExceptionResponsePattern response = ExceptionResponsePattern.builder()
                .message("email already exists")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
    }

}
