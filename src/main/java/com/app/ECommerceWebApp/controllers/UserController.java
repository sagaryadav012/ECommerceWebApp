package com.app.ECommerceWebApp.controllers;

import com.app.ECommerceWebApp.dtos.userDtos.RegisterUserDto;
import com.app.ECommerceWebApp.exceptions.userExceptions.UserExistsException;
import com.app.ECommerceWebApp.exceptions.userExceptions.UserNotExistsException;
import com.app.ECommerceWebApp.models.User;
import com.app.ECommerceWebApp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserDto registerUserDto){
        try {
            User user = this.userService.registerUser(registerUserDto.getName(), registerUserDto.getMail(), registerUserDto.getPassword());
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (UserExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/fetch/{mail}")
    public ResponseEntity<User> getUserByName(@PathVariable String mail){
        User user = this.userService.findByMail(mail);
        if(user == null) try {
            throw new UserNotExistsException("User not found");
        } catch (UserNotExistsException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
