package com.capstoneproject.exceptions;
/**
 * This exception is about Duplicate Email Exception.
 */
public class DuplicateKeyException extends RuntimeException {
    /**
     * This method returns the Exception message.
     * @param message of String type.
     */
    public DuplicateKeyException(final String message) {
        super(message);
    }
}
