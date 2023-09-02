package com.capstoneproject.exceptions;
/**
 * This class is about Validation Exception.
 */
public class ValidationException extends RuntimeException {
    /**
     * This method returns the Exception message.
     * @param message of String type.
     */
    public ValidationException(final String message) {
        super(message);
    }
}
