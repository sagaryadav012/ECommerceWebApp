package com.app.ECommerceWebApp.services;

import com.app.ECommerceWebApp.controllers.CartController;
import com.app.ECommerceWebApp.controllers.ProductController;
import com.app.ECommerceWebApp.exceptions.cartItemExceptions.ProductExistException;
import com.app.ECommerceWebApp.exceptions.cartItemExceptions.ProductNotExistException;
import com.app.ECommerceWebApp.models.Cart;
import com.app.ECommerceWebApp.models.CartItem;
import com.app.ECommerceWebApp.models.Product;
import com.app.ECommerceWebApp.repositories.CartItemRepo;
import jakarta.persistence.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService{
    private ProductController productController;
    private CartItemRepo cartItemRepo;
    private CartController cartController;
    @Override
    public CartItem addCartItem(long cart_id, long product_id, int quantity) throws ProductExistException {
        /*
            1. check product exists in products table, if not throw exception.
            2. check product already exists in cart, if yes just throw exception says product already in cart.
            3. add product to cart, update cartItem table.

         */

        Product product = getProduct(product_id);
        Cart cart = getCart(cart_id);

        if(IsProductExists(cart, product)){
            throw new ProductExistException("Product already is there in cart, Please update quantity");
        }

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setQuantity(quantity);

        return this.cartItemRepo.save(cartItem);
    }

    @Override
    public CartItem removerCartItem(long cart_id, long product_id) throws ProductNotExistException {
        /*
            1. check product exists in cart, If not throw exception
            2. else remove item from cart.
         */

        Cart cart = getCart(cart_id);
        Product product = getProduct(product_id);
        if(!IsProductExists(cart, product)){
            throw new ProductNotExistException("Product Not Found In Cart!");
        }

        return this.cartItemRepo.deleteCartItem(cart, product);
    }

    @Override
    public CartItem updateQuantity(long cart_id, long product_id, int quantity) throws ProductNotExistException {
        Cart cart = getCart(cart_id);
        Product product = getProduct(product_id);
        if(!IsProductExists(cart, product)){
            throw new ProductNotExistException("Product Not Found In Cart!");
        }
        return this.cartItemRepo.updateQuantity(quantity, cart, product);
    }

    public boolean IsProductExists(Cart cart, Product product){
        long countProducts = this.cartItemRepo.countProducts(cart, product);
        return countProducts > 0;
    }

    public Cart getCart(long cart_id){
        ResponseEntity<Cart> cartResponseEntity = this.cartController.viewCart(cart_id);
        return cartResponseEntity.getBody();
    }

    public Product getProduct(long product_id){
        ResponseEntity<Product> productResponseEntity = this.productController.getProduct(product_id);
        return productResponseEntity.getBody();
    }
}
