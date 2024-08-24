package com.app.ECommerceWebApp.repositories;

import com.app.ECommerceWebApp.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
