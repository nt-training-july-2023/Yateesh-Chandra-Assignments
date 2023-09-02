package com.capstoneproject.exceptions;
/**
 * This exception is about Authentication Exception.
 */
public class AuthenticationException extends RuntimeException {
    /**
     * This method returns the Exception message.
     * @param message of String type.
     */
    public AuthenticationException(final String message) {
        super(message);
    }
}
