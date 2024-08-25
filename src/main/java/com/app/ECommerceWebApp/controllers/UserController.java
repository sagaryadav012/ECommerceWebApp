package com.app.ECommerceWebApp.controllers;

import com.app.ECommerceWebApp.dtos.userDtos.LoginUserDTO;
import com.app.ECommerceWebApp.dtos.userDtos.LogoutUserDTO;
import com.app.ECommerceWebApp.dtos.userDtos.RegisterUserDto;
import com.app.ECommerceWebApp.exceptions.userExceptions.UserExistsException;
import com.app.ECommerceWebApp.exceptions.userExceptions.UserNotExistsException;
import com.app.ECommerceWebApp.models.Token;
import com.app.ECommerceWebApp.models.User;
import com.app.ECommerceWebApp.services.user.UserService;
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

    @PostMapping("/login")
    public ResponseEntity<Token> loginUser(@RequestBody LoginUserDTO loginUserDTO){
        try {
            Token token = this.userService.loginUser(loginUserDTO.getMail(), loginUserDTO.getPassword());
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logoutUser(@RequestBody LogoutUserDTO logoutUserDTO){
        try {
            this.userService.logoutUser(logoutUserDTO.getValue());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<User> getUSer(@PathVariable long id){
        try {
            User user = this.userService.getUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotExistsException e) {
            throw new RuntimeException(e);
        }
    }

}
