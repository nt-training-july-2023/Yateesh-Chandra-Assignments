package com.capstoneproject.exceptions;
/**
 * This class is about Internal Server Error Exception.
 */
public class InternalServerErrorException extends RuntimeException {
    /**
     * This method returns the Exception message.
     * @param message of String type.
     */
    public InternalServerErrorException(final String message) {
        super(message);
    }
}
