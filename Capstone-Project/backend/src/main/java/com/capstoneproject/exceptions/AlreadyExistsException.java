package com.capstoneproject.exceptions;
/**
 * This is the AlreadyExistsException.
 */
public class AlreadyExistsException extends RuntimeException {

    /**
     * This is the Serial Version Id.
     */
    private static final Long serialVersionUID = 1L;
    /**
     * This is AlreadyExistsException.
     * @param message .
     */
    public AlreadyExistsException(final String message) {
        super(message);
    }
    /**
     * This is no args constructor for the exception.
     */
    public AlreadyExistsException() {
    }
}
