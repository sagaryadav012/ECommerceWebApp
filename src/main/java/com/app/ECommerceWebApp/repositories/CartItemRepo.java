package com.app.ECommerceWebApp.repositories;

import com.app.ECommerceWebApp.models.Cart;
import com.app.ECommerceWebApp.models.CartItem;
import com.app.ECommerceWebApp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    @Query("SELECT COUNT(c) FROM cart_items c WHERE c.cart = :cartId AND c.product = :productId")
    long countProducts(@Param("cartId") Cart cartId, @Param("productId")Product productId);

    @Modifying
    @Query("DELETE FROM cart_items c WHERE c.cart = :cartId AND c.product = :productId")
    CartItem deleteCartItem(@Param("cartId") Cart cartId, @Param("productId")Product productId);

    Optional<CartItem> findCartItemByCartAndProduct(Cart cart, Product product);

    @Query("UPDATE cart_items c SET c.quantity = :quantity WHERE c.cart = :cartId AND c.product = :productId")
    void updateQuantity(@Param("quantity") int quantity, @Param("cartId") Cart cartId, @Param("productId")Product productId);

}
