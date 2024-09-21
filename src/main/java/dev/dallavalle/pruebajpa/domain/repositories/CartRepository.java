package dev.dallavalle.pruebajpa.domain.repositories;

import dev.dallavalle.pruebajpa.domain.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}