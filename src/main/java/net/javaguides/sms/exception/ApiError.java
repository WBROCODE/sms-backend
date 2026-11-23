package net.javaguides.sms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private Instant timestamp;   // when it happened
    private int status;          // 404, 400, 500...
    private String message;      // what went wrong
    private String path;         // which URL
}

