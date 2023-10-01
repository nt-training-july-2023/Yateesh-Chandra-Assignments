package com.capstoneproject.response;

/**
 * This is the Error Response Class.
 */
public class Response {

    /**
     * code variable is of integer type to display status code.
     */
    private int statusCode;

    /**
     * This is message for the status code.
     */
    private String message;

    /**
     * This is the constructor for error response.
     * @param errorCode of integer type.
     * @param errorMessage of String type.
     */
    public Response(final int errorCode, final String errorMessage) {
        this.statusCode = errorCode;
        this.message = errorMessage;
    }

    /**
     * This is Constructor with no arguments.
     */
    public Response() {
        super();
    }

    /**
     * This is the getter method for code.
     * @return the Code.
     */
    public final int getCode() {
        return statusCode;
    }

    /**
     * This is the setter method for the error code.
     * @param errorCode of integer type.
     */
    public final void setCode(final int errorCode) {
        this.statusCode = errorCode;
    }

    /**
     * This is the getter method for the message.
     * @return the message.
     */
    public final String getMessage() {
        return message;
    }

    /**
     * This is the Error Message setter method.
     * @param errorMessage of String type.
     */
    public final void setMessage(final String errorMessage) {
        this.message = errorMessage;
    }

}
