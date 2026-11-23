package net.javaguides.sms.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice                       // global: applies to ALL controllers
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
}