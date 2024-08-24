package com.app.ECommerceWebApp.exceptions.cartItemExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CartItemGlobalExceptionHandler {

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<String> productNotExistException(ProductNotExistException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductExistException.class)
    public ResponseEntity<String> productExistException(ProductExistException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<String> cartItemNotFoundException(CartItemNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientProductQuantityException.class)
    public ResponseEntity<String> insufficientProductQuantityException(InsufficientProductQuantityException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
