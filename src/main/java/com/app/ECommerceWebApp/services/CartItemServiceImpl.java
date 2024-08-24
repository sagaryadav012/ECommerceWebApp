package com.app.ECommerceWebApp.services;

import com.app.ECommerceWebApp.controllers.ProductController;
import com.app.ECommerceWebApp.models.Cart;
import com.app.ECommerceWebApp.models.CartItem;
import com.app.ECommerceWebApp.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService{
    private ProductController productController;
    @Override
    public CartItem addCartItem(long cart_id, long product_id, int quantity) {
        /*
            1. check product exists in products table, if not throw exception.
            2. check product already exists in cart, if yes just throw exception says product already in cart.
            3. add product to cart, update cartItem table.

         */
        ResponseEntity<Product> product = this.productController.getProduct(product_id);
//        this.
        return null;
    }

    @Override
    public void removerCartItem(long cart_id, long product_id) {

    }

    @Override
    public CartItem updateQuantity(long cart_id, long product_id) {
        return null;
    }
}
