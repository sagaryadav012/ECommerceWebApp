package com.app.ECommerceWebApp.services;

import com.app.ECommerceWebApp.exceptions.orderExceptions.OrderNotFoundException;
import com.app.ECommerceWebApp.models.Cart;
import com.app.ECommerceWebApp.models.Order;
import com.app.ECommerceWebApp.models.OrderedProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order createOrder(long user_id);
    Order fetchOrder(long order_id) throws OrderNotFoundException;
    List<Order> fetchOrders();
}
