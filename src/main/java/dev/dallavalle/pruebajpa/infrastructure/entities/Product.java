package dev.dallavalle.pruebajpa.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private long stock;
    private double price;

    public void subtractUnits(long unitsRequested) {
        if (unitsRequested > stock) {
            throw new IllegalArgumentException("Hay menos stock del que se puede restar");
        }
        
        stock -= unitsRequested;
    }
}
