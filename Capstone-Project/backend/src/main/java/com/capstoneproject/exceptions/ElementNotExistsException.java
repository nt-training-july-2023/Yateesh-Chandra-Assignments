package com.capstoneproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is ElementNotExist exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
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
