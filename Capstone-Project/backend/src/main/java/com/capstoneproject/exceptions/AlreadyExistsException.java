package com.capstoneproject.exceptions;

public class AlreadyExistsException extends RuntimeException {

    private static final Long serialVersionUID = 1L;
    public AlreadyExistsException(final String message) {
        super(message);
    }
    
    public AlreadyExistsException() {
    }
}
