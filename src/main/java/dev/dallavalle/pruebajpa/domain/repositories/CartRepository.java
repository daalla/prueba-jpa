package dev.dallavalle.pruebajpa.domain.repositories;

import dev.dallavalle.pruebajpa.domain.entities.Cart;
import dev.dallavalle.pruebajpa.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}