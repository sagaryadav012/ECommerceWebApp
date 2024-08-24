package com.app.ECommerceWebApp.exceptions.cartExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CartGlobalExceptionHandler {

    @ExceptionHandler(CartNotExistsException.class)
    public ResponseEntity<String> cartNotExistsException(CartNotExistsException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

    }
}
