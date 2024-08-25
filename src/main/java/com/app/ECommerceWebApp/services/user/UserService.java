package com.app.ECommerceWebApp.services.user;

import com.app.ECommerceWebApp.exceptions.userExceptions.IncorrectPasswordException;
import com.app.ECommerceWebApp.exceptions.userExceptions.InvalidTokenException;
import com.app.ECommerceWebApp.exceptions.userExceptions.UserExistsException;
import com.app.ECommerceWebApp.exceptions.userExceptions.UserNotExistsException;
import com.app.ECommerceWebApp.models.Token;
import com.app.ECommerceWebApp.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registerUser(String name, String mail, String password) throws UserExistsException;
    Token loginUser(String mail, String password) throws UserNotExistsException, IncorrectPasswordException;
    void logoutUser(String value) throws InvalidTokenException;
    User findByMail(String mail) throws UserNotExistsException;
    boolean validate_token(String value) throws InvalidTokenException;
    User getUser(long id) throws UserNotExistsException;
}
