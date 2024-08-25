package com.app.ECommerceWebApp.services.user;

import com.app.ECommerceWebApp.exceptions.userExceptions.IncorrectPasswordException;
import com.app.ECommerceWebApp.exceptions.userExceptions.InvalidTokenException;
import com.app.ECommerceWebApp.exceptions.userExceptions.UserExistsException;
import com.app.ECommerceWebApp.exceptions.userExceptions.UserNotExistsException;
import com.app.ECommerceWebApp.models.Cart;
import com.app.ECommerceWebApp.models.Token;
import com.app.ECommerceWebApp.models.User;
import com.app.ECommerceWebApp.repositories.TokenRepo;
import com.app.ECommerceWebApp.repositories.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService{
    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepo tokenRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepo = tokenRepo;
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
    public Token loginUser(String mail, String password) throws UserNotExistsException, IncorrectPasswordException {
        User user = this.findByMail(mail);
        String encodedPassword = user.getPassword();
        boolean matches = this.bCryptPasswordEncoder.matches(password, encodedPassword);

        if(!matches) throw new IncorrectPasswordException("Incorrect Password!");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 5);
        Date fiveDaysLater = c.getTime();

        String value = RandomStringUtils.randomAlphanumeric(128);

        Token token = new Token();
        token.setActive(true);
        token.setUser(user);
        token.setExpireAt(fiveDaysLater);
        token.setValue(value);

        return this.tokenRepo.save(token);
    }

    @Override
    public void logoutUser(String value) throws InvalidTokenException {
        // step1 : Fetch token from db using token value;
        // step2 : If not found throw exception, else we have two options 1. Hard Delete, 2.Soft Delete.
        // Hard Delete deletes token data from db, Soft Delete sets isActive to false.
        // step3 : set isActive to false, and update token in db

        Token token = this.tokenRepo.findTokenByValue(value).orElseThrow(() -> new InvalidTokenException("Token not found"));
        if(token.isActive()){
            token.setActive(false);
            this.tokenRepo.save(token);
        }
    }

    @Override
    public User findByMail(String mail) throws UserNotExistsException {
        return userRepo.findUserByMail(mail).orElseThrow(() -> new UserNotExistsException("User not found!"));
    }

    @Override
    public boolean validate_token(String value) throws InvalidTokenException {
        /*
        1. Fetch the token from db using value (select * from tokens where value = {value})
        2. If token is not present in db, throw exception
        3. Else, check whether the token has expired or not
        4. If token is expired, then throw an Exception
        5. Else you are going to return the token
         */
        Token token = this.tokenRepo.findTokenByValue(value).orElseThrow(() -> new InvalidTokenException("Token not found"));
        Date expireAt = token.getExpireAt();
        Date now = new Date();

        if(now.after(expireAt) || !token.isActive()){
            throw new InvalidTokenException("Login has expired");
        }
        return true;
    }

    @Override
    public User getUser(long id) throws UserNotExistsException {
        return this.userRepo.findById(id).orElseThrow(() -> new UserNotExistsException("No User Found!"));
    }
}
