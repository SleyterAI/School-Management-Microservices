package com.sai.enrollment_service.Exception;

public class FeignServiceException extends RuntimeException {

    public FeignServiceException(String message) {
        super(message);
    }

    public FeignServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}