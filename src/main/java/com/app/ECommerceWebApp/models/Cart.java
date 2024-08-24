package com.app.ECommerceWebApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "carts")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;
    private double totalAmount;
}
