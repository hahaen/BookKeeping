package com.hahaen.bookkeeping.exception;

import lombok.Data;

/**
 * HC Accounting Service Exception.
 */
@Data
public class ServiceException extends RuntimeException {
    private int statusCode;
    private String errorCode;
    private ServiceException.ErrorType errorType;

    public enum ErrorType {
        Client,
        Service,
        Unknown
    }

    public ServiceException(String message) {
        super(message);
    }
}
