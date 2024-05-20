package com.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private Boolean success;
    private int responseCode;
    private String message;
    private LocalDateTime dateTime;
    private List<String> details;
    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
        this.success = Boolean.FALSE;
        this.dateTime = LocalDateTime.now();
    }

    public ErrorResponse(String message, int responseCode, List<String> details) {
        this.message = message;
        this.responseCode =responseCode;
        this.details = details;
        this.success = Boolean.FALSE;
        this.dateTime = LocalDateTime.now();
    }
}
