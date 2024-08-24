package com.app.ECommerceWebApp.exceptions.orderExceptions;

public class OrderNotFoundException extends Exception{
    public OrderNotFoundException(String message) {
        super(message);
    }
}
