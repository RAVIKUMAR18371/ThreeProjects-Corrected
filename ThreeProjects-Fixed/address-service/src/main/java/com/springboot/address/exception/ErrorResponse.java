package com.springboot.address.exception;

import java.time.LocalDateTime;
public class ErrorResponse {
    private final String message;
    private final String status;
    private final LocalDateTime timestamp;

    public ErrorResponse(String message, String status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now(); // Auto generated on error creation
    }

    //Getter
    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
}
