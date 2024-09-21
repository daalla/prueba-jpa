package dev.dallavalle.pruebajpa.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
@AllArgsConstructor
public class ProductInCart {
    @EmbeddedId
    private ProductInCartId id;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @MapsId(value = "productId")
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne
    @MapsId(value = "cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;
    
    private long quantity;

    public void addUnits(long unitsRequested) {
        quantity += unitsRequested;
    }

    public void restoreProductStock() {
        product.restoreStock(quantity);
    }
}
