package com.capstoneproject.exceptions;
/**
 * This exception is about Entity Not Found Exception.
 */
public class EntityNotFoundException extends RuntimeException {
    /**
     * This method returns the Exception message.
     * @param message of String type.
     */
    public EntityNotFoundException(final String message) {
        super(message);
    }
}
