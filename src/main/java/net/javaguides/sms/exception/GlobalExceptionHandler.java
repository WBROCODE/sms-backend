package net.javaguides.sms.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice                       // global: applies to ALL controllers
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)   // which exception this method handles
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex,
                                                   HttpServletRequest request) {
        ApiError error = new ApiError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),    // 404
                ex.getMessage(),                 // the message from orElseThrow
                request.getRequestURI());        // e.g. /api/students/999
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }



    // ?sort=age → the field doesn't exist → the client's mistake → 400
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ApiError> handleBadSort(PropertyReferenceException ex,
                                                  HttpServletRequest request) {
        ApiError error = new ApiError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // duplicate email → breaks the unique rule → conflict → 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDuplicate(DataIntegrityViolationException ex,
                                                    HttpServletRequest request) {
        ApiError error = new ApiError(Instant.now(), HttpStatus.CONFLICT.value(),
                "Email already exists", request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // anything else → a real server problem → 500 (the safety net)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleOther(Exception ex, HttpServletRequest request) {
        ApiError error = new ApiError(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Something went wrong", request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}