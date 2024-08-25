package com.app.ECommerceWebApp.services.cartItem;

import com.app.ECommerceWebApp.exceptions.cartItemExceptions.CartItemNotFoundException;
import com.app.ECommerceWebApp.exceptions.cartItemExceptions.InsufficientProductQuantityException;
import com.app.ECommerceWebApp.exceptions.cartItemExceptions.ProductExistException;
import com.app.ECommerceWebApp.exceptions.cartItemExceptions.ProductNotExistException;
import com.app.ECommerceWebApp.models.CartItem;
import org.springframework.stereotype.Service;

@Service
public interface CartItemService {
    CartItem addCartItem(long cart_id, long product_id, int quantity) throws ProductExistException, InsufficientProductQuantityException;
    CartItem removerCartItem(long cart_id, long product_id) throws CartItemNotFoundException;
    void updateQuantity(long cart_id, long product_id, int quantity) throws ProductNotExistException;
}
