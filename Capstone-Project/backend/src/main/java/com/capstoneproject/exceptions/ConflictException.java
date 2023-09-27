package com.capstoneproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is about Entity Not Found Exception.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
    /**
     * This method returns the Exception message.
     * @param message of String type.
     */
    public ConflictException(final String message) {
        super(message);
    }
}
