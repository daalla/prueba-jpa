package dev.dallavalle.pruebajpa.infrastructure.repositories;

import dev.dallavalle.pruebajpa.infrastructure.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}