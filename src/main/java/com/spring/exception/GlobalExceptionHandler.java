package com.spring.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> handleRecordNotFound(RecordNotFoundException ex){
        int code =ex.getCode() !=0 ? ex.getCode() : HttpStatus.NOT_FOUND.value();
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), code,Arrays.asList(ex.getMessage()));
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);

    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<?> handleDuplicatedRecordException(DuplicateRecordException ex) {
        int code = ex.getCode() !=0 ? ex.getCode() : HttpStatus.CONTINUE.value();
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage(), code, Arrays.asList(ex.getMessage()));

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {
        ex.printStackTrace();
        List<String> errors =  new ArrayList<>();
        errors.add(ex.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.toString(), errors);

        return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //More than one Exception(Postman-> request body)

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getDefaultMessage());
        }
        for (ObjectError objectError: ex.getBindingResult().getGlobalErrors()) {
            errors.add(objectError.getDefaultMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), errors);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
