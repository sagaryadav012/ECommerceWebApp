package com.app.ECommerceWebApp.exceptions.cartItemExceptions;

public class InsufficientProductQuantityException extends Exception{
    public InsufficientProductQuantityException(String msg){
        super(msg);
    }
}
