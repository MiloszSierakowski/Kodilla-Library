package com.example.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CopyOfBookNotFoundException.class)
    public ResponseEntity<Object> handleCopyOfBookNotFoundException(CopyOfBookNotFoundException exception) {
        return new ResponseEntity<>("Copy of book with given id doesn't exists", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseEntity<Object> handleRentalNotFoundException(RentalNotFoundException exception) {
        return new ResponseEntity<>("Rental with given id doesn't exists", HttpStatus.NOT_FOUND);
    }
}
