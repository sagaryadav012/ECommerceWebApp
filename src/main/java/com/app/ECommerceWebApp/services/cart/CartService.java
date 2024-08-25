package com.app.ECommerceWebApp.services.cart;

import com.app.ECommerceWebApp.exceptions.cartExceptions.CartNotExistsException;
import com.app.ECommerceWebApp.models.Cart;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Cart viewCart(long id) throws CartNotExistsException;
    Cart addCartItem(long cart_id, long product_id, int quantity) throws CartNotExistsException;
    Cart removeCartItem(long cart_id, long product_id) throws CartNotExistsException;
    Cart updateQuantity(long cart_id, long product_id, int quantity) throws CartNotExistsException;
}
