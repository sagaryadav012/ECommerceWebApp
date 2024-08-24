package com.app.ECommerceWebApp.repositories;

import com.app.ECommerceWebApp.models.Cart;
import com.app.ECommerceWebApp.models.CartItem;
import com.app.ECommerceWebApp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    @Query("SELECT COUNT(*) FROM CartItem c WHERE c.cart = :cartId AND c.product = :productId")
    public long countProducts(@Param("cartId") Cart cartId, @Param("productId")Product productId);
}
