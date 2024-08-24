package com.app.ECommerceWebApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String mail;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
}
