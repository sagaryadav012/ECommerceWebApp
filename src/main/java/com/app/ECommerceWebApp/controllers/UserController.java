package com.app.ECommerceWebApp.controllers;

import com.app.ECommerceWebApp.dtos.userDtos.RegisterUserDto;
import com.app.ECommerceWebApp.exceptions.userExceptions.UserExistsException;
import com.app.ECommerceWebApp.exceptions.userExceptions.UserNotExistsException;
import com.app.ECommerceWebApp.models.User;
import com.app.ECommerceWebApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserDto registerUserDto){
        try {
            User user = this.userService.registerUser(registerUserDto.getName(), registerUserDto.getMail(), registerUserDto.getPassword());
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (UserExistsException e) {
            throw new RuntimeException(e);
        }
    }
}
