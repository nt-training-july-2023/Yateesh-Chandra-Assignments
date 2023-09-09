package com.capstoneproject.exceptions;
/**
 * This is ElementNotExist exception.
 */
public class ElementNotExistsException extends RuntimeException {

    /**
     * This is Serial Version Id.
     */
    private static final Long serialVersionUID = 1L;
    /**
     * this is the message argument constructor.
     * @param message .
     */
    public ElementNotExistsException(final String message) {
        super(message);
    }

    /**
     * This is No arguments constructor.
     */
    public ElementNotExistsException() {
    }
}
