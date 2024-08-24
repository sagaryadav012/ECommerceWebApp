package com.app.ECommerceWebApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name = "user_orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date orderDate;
    private double totalAmount;
    private OrderStatus orderStatus;
//    private List<>
    @ManyToOne
    private User userId;
}
