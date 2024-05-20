package com.spring.exception;

import lombok.Data;

@Data
public class RecordNotFoundException extends RuntimeException{

    private int code;

    public RecordNotFoundException() {
        super("Record Not Found");
        this.code = 404;
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
