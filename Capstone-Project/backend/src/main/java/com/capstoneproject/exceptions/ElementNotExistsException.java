package com.capstoneproject.exceptions;

public class ElementNotExistsException extends RuntimeException {

    private static final Long serialVersionUID = 1L;
    public ElementNotExistsException(final String message) {
        super(message);
    }
    
    public ElementNotExistsException() {
    }
}
