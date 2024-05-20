package com.spring.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class DuplicateRecordException extends RuntimeException{

    private int code = 409;

    public DuplicateRecordException() {
        super("Duplicated Record!!!!!");
        this.code = HttpStatus.CONFLICT.value();
    }

    public DuplicateRecordException(String message) {
        super(message);
    }
}
