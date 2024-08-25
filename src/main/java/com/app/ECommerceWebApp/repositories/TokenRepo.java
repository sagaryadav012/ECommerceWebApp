package com.app.ECommerceWebApp.repositories;

import com.app.ECommerceWebApp.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {
    Optional<Token> findTokenByValue(String value);
}
