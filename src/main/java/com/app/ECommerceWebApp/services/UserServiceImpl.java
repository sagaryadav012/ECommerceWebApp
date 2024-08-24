package com.app.ECommerceWebApp.services;

import com.app.ECommerceWebApp.exceptions.userExceptions.UserExistsException;
import com.app.ECommerceWebApp.exceptions.userExceptions.UserNotExistsException;
import com.app.ECommerceWebApp.models.User;
import com.app.ECommerceWebApp.repositories.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserServiceImpl implements UserService{
    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User registerUser(String name, String mail, String password) throws UserExistsException {
        User userByMail = this.findByMail(mail);
        if(userByMail != null) throw new UserExistsException("Mail ID already registered, Please do login!");

        User user = new User();
        user.setName(name);
        user.setMail(mail);
        String encodedPassword = this.bCryptPasswordEncoder.encode(password);
        user.setPassword(encodedPassword);
        return this.userRepo.save(user);
    }

    @Override
    public void loginUser(String mail, String password) throws UserNotExistsException {
        User userByMail = this.findByMail(mail);
        if(userByMail == null) throw new UserNotExistsException("Mail Id not registered, Please do register");

        String encodedPassword = userByMail.getPassword();
        boolean matches = this.bCryptPasswordEncoder.matches(password, encodedPassword);

    }

    @Override
    public void logoutUser(long id) {

    }

    @Override
    public User findByMail(String mail) {
        Optional<User> userByMail = userRepo.findUserByMail(mail);
        return userByMail.orElse(null);
    }
}
