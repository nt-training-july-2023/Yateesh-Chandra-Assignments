package com.capstoneproject.exceptions;
/**
 * This is NoInputException.
 */
public class NoInputException extends RuntimeException {

    /**
     * This is the Serial Version Id.
     */
    private static final Long serialVersionUID = 1L;

    /**
     * This is constructor.
     * @param message .
     */
    public NoInputException(final String message) {
        super(message);
    }

    /**
     * This is No arguments Constructor.
     */
    public NoInputException() {
    }
}
