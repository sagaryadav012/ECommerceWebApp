package com.app.ECommerceWebApp.exceptions.cartItemExceptions;

public class ProductNotExistException extends Exception{
    public ProductNotExistException(String msg){
        super(msg);
    }
}
