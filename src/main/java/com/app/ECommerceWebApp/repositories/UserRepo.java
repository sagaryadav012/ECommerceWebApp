package com.app.ECommerceWebApp.repositories;

import com.app.ECommerceWebApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByMail(String mail);
}
