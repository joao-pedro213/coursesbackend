package com.joaocapobiango.coursesbackend.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleConstraintViolationException(ConstraintViolationException exception) {
        var violations = new ArrayList<String>();
        for (ConstraintViolation violation : exception.getConstraintViolations()) {
            violations.add("%s %s".formatted(violation.getPropertyPath(),  violation.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(violations);
    }

}