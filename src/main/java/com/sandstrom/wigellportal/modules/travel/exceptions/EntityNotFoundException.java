package com.sandstrom.wigellportal.modules.travel.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {

    }
    public EntityNotFoundException(String message) {
        super(message);
    }
    public EntityNotFoundException(String message, Throwable cause) {
        super(message);
    }
    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
