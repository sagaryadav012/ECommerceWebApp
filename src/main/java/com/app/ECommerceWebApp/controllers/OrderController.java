package com.app.ECommerceWebApp.controllers;

import com.app.ECommerceWebApp.dtos.orderDtos.CreateOrderDTO;
import com.app.ECommerceWebApp.exceptions.orderExceptions.OrderNotFoundException;
import com.app.ECommerceWebApp.models.Order;
import com.app.ECommerceWebApp.services.order.OrderService;
import com.app.ECommerceWebApp.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    private AuthUtils authUtils;

    @Autowired
    public OrderController(OrderService orderService, AuthUtils authUtils) {
        this.authUtils = authUtils;
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDTO createOrderDTO, @RequestHeader String value){
        if(!authUtils.validate_token(value)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Order order = this.orderService.createOrder(createOrderDTO.getUser_id());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable long id, @RequestHeader String value){
        if(!authUtils.validate_token(value)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        try {
            Order order = this.orderService.fetchOrder(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getOrders(){
        List<Order> orders = this.orderService.fetchOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
