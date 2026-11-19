package com.sai.enrollment_service.Exception;

import feign.FeignException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, Object>> buildResponse(
            HttpStatus status,
            String message) {

        Map<String, Object> response = new HashMap<>();

        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);

        return ResponseEntity.status(status).body(response);
    }

    // 404 - Recurso no encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            ResourceNotFoundException ex) {

        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // 409 - Conflicto de negocio
    @ExceptionHandler({
            DuplicateEnrollmentException.class,
            BusinessException.class
    })
    public ResponseEntity<Map<String, Object>> handleBusiness(
            RuntimeException ex) {

        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    // 400 - Errores de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Invalid request");

        return buildResponse(HttpStatus.BAD_REQUEST, message);
    }

    // Errores de comunicación con otros microservicios
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, Object>> handleFeign(
            FeignException ex) {

        if (ex.status() == 404) {
            return buildResponse(
                    HttpStatus.NOT_FOUND,
                    "Student or course not found in external service"
            );
        }

        return buildResponse(
                HttpStatus.SERVICE_UNAVAILABLE,
                "An external service is unavailable"
        );
    }

    // 409 - Restricciones de base de datos
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrity(
            DataIntegrityViolationException ex) {

        return buildResponse(
                HttpStatus.CONFLICT,
                "The operation violates a database constraint"
        );
    }

    // 500 - Error inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(
            Exception ex) {

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred"
        );
    }
}
