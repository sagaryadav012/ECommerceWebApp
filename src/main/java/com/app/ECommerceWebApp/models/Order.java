package com.app.ECommerceWebApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity(name = "user_orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date orderDate;
    private double totalAmount;
    private OrderStatus orderStatus;

    @ManyToOne
    private User userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderedProduct> orderedProductList;
}
