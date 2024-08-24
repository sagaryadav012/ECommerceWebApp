package com.app.ECommerceWebApp.repositories;

import com.app.ECommerceWebApp.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
}
