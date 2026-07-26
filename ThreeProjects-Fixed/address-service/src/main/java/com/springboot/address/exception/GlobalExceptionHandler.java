package com.springboot.address.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Listens to exceptions thrown anywhere in the application
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getStatus().name());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getStatus().name());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(MissingParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParameter(MissingParameterException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getStatus().name());
        return new ResponseEntity<>(response, ex.getStatus());
    }
}