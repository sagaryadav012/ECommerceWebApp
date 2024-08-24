package com.app.ECommerceWebApp.exceptions.orderExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> orderNotFoundExceptionHandler(OrderNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
