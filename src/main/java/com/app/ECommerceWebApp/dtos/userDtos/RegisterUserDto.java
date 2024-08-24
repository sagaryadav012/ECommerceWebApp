package com.app.ECommerceWebApp.dtos.userDtos;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String name;
    private String mail;
    private String password;
}
