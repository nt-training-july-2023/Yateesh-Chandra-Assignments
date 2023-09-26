package com.capstoneproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is the AlreadyExistsException.
 */
@ResponseStatus(HttpStatus.FOUND)
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
