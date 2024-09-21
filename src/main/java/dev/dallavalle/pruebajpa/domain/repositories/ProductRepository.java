package dev.dallavalle.pruebajpa.domain.repositories;

import dev.dallavalle.pruebajpa.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}