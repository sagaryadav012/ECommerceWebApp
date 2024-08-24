package com.app.ECommerceWebApp.services;

import com.app.ECommerceWebApp.exceptions.userExceptions.UserExistsException;
import com.app.ECommerceWebApp.exceptions.userExceptions.UserNotExistsException;
import com.app.ECommerceWebApp.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registerUser(String name, String mail, String password) throws UserExistsException;
    void loginUser(String mail, String password) throws UserNotExistsException;
    void logoutUser(long id);
    User findByMail(String mail) throws UserNotExistsException;
}
