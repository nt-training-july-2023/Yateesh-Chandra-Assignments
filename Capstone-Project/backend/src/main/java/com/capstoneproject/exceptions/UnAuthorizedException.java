package com.capstoneproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is about Authentication Exception.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {

    /**
     * This method returns the Exception message.
     * @param message of String type.
     */
    public UnAuthorizedException(final String message) {
        super(message);
    }
}