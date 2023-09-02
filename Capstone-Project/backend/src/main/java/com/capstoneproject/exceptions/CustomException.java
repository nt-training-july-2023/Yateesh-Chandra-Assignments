package com.capstoneproject.exceptions;
/**
 * This class is about Custom Exception.
 */
public class CustomException extends RuntimeException {
    /**
     * This method returns the Exception message.
     * @param message of String type.
     */
    public CustomException(final String message) {
        super(message);
    }
}
