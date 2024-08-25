package com.app.ECommerceWebApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name = "tokens")
@Data
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User user;
    private String value;
    private Date expireAt;
    private boolean isActive;
}
