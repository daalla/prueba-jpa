package dev.dallavalle.pruebajpa.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    // todo: analizar si lo dejo o no como para acceder desde el usuario directamente o no
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Cart cart;
}
