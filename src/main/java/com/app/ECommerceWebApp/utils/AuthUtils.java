package com.app.ECommerceWebApp.utils;

import com.app.ECommerceWebApp.exceptions.userExceptions.InvalidTokenException;
import com.app.ECommerceWebApp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {
    private UserService userService;

    @Autowired
    public AuthUtils(UserService userService) {
        this.userService = userService;
    }

    public boolean validate_token(String value) {
        try {
            return this.userService.validate_token(value);
        } catch (InvalidTokenException e) {
            throw new RuntimeException(e);
        }
    }
}
