package com.capstoneproject.exceptions;

public class NoInputException extends RuntimeException {

    private static final Long serialVersionUID = 1L;
    public NoInputException(final String message) {
        super(message);
    }
    
    public NoInputException() {
    }
}
