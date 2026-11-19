package com.sai.enrollment_service.Exception;

public class DuplicateEnrollmentException extends RuntimeException {

    public DuplicateEnrollmentException(String message) {
        super(message);
    }
}
