package com.app.ECommerceWebApp.services;

import com.app.ECommerceWebApp.models.CartItem;
import org.springframework.stereotype.Service;

@Service
public interface CartItemService {
    CartItem addCartItem(long cart_id, long product_id, int quantity);
    void removerCartItem(long cart_id, long product_id);
    CartItem updateQuantity(long cart_id, long product_id);
}
