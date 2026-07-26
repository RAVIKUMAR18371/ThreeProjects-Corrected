package com.Employee.service.exception;

import org.springframework.http.HttpStatus;

public class MissingParameterException extends RuntimeException {
    private final HttpStatus status;

    public MissingParameterException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

