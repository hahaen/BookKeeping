package com.hahaen.bookkeeping.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String Code;
    private ServiceException.ErrorType errorType;
    private String message;
    private int statusCode;

}