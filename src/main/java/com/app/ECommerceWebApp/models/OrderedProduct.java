package com.app.ECommerceWebApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "ordered_products")
@Data
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Product product;
    private int quantity;
    @JsonIgnore
    @ManyToOne
    private Order order;
}