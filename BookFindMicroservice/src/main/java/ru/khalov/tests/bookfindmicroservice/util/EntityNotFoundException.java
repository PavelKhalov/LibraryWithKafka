package ru.khalov.tests.bookfindmicroservice.util;

import java.time.LocalDateTime;

public class EntityNotFoundException extends RuntimeException{
    private String message;
    private LocalDateTime timestamp;

    public EntityNotFoundException(String message, String message1, LocalDateTime timestamp) {
        super(message);
        this.message = message1;
        this.timestamp = timestamp;
    }

    public EntityNotFoundException(Throwable cause, String message, LocalDateTime timestamp) {
        super(cause);
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
