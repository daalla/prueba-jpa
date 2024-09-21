package dev.dallavalle.pruebajpa.infrastructure.repositories;

import dev.dallavalle.pruebajpa.infrastructure.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}