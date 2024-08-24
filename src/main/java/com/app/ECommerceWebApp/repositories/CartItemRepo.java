package com.app.ECommerceWebApp.repositories;

import com.app.ECommerceWebApp.models.Cart;
import com.app.ECommerceWebApp.models.CartItem;
import com.app.ECommerceWebApp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    @Query("SELECT COUNT(*) FROM CartItem c WHERE c.cart = :cartId AND c.product = :productId")
    long countProducts(@Param("cartId") Cart cartId, @Param("productId")Product productId);

    @Query("DELETE FROM CartItem c WHERE c.cart = :cartId AND c.product = :productId")
    CartItem deleteCartItem(@Param("cartId") Cart cartId, @Param("productId")Product productId);

    @Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.cart = :cartId AND c.product = :productId")
    CartItem updateQuantity(@Param("quantity") int quantity, @Param("cartId") Cart cartId, @Param("productId")Product productId);

}
