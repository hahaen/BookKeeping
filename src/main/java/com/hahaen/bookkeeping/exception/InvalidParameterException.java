package com.hahaen.bookkeeping.exception;

import org.springframework.http.HttpStatus;

public class InvalidParameterException extends ServiceException {
    /**
     * Constructor for InvalidParameterException.
     *
     * @param message throw message
     */
    public InvalidParameterException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
        this.setErrorCode("INVALID_PARAMETER");
        this.setErrorType(ErrorType.Client);
    }
}
