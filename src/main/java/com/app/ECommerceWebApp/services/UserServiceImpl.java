package com.app.ECommerceWebApp.services;

import com.app.ECommerceWebApp.exceptions.userExceptions.UserExistsException;
import com.app.ECommerceWebApp.exceptions.userExceptions.UserNotExistsException;
import com.app.ECommerceWebApp.models.Cart;
import com.app.ECommerceWebApp.models.User;
import com.app.ECommerceWebApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User registerUser(String name, String mail, String password) throws UserExistsException {
        try {
            User user = this.findByMail(mail);
            throw new UserExistsException("Mail Already Registered, Please do login");
        } catch (UserNotExistsException e) {
            User user = new User();
            user.setName(name);
            user.setMail(mail);
            String encodedPassword = this.bCryptPasswordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setCart(new Cart());
            return this.userRepo.save(user);
        }
    }

    @Override
    public void loginUser(String mail, String password) throws UserNotExistsException {
        User user = this.findByMail(mail);
        String encodedPassword = user.getPassword();
        boolean matches = this.bCryptPasswordEncoder.matches(password, encodedPassword);

    }

    @Override
    public void logoutUser(long id) {

    }

    @Override
    public User findByMail(String mail) throws UserNotExistsException {
        return userRepo.findUserByMail(mail).orElseThrow(() -> new UserNotExistsException("User not found!"));
    }
}
