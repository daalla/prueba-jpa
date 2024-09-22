package dev.dallavalle.pruebajpa.domain.entities;

import dev.dallavalle.pruebajpa.presentation.dtos.CartDetailDto;
import dev.dallavalle.pruebajpa.presentation.dtos.ProductInCartDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cart {
    @Id 
    @Column(name = "owner_id")
    private Long ownerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "owner_id")
    private User user;
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)  // todo: averiguar qué hace el orphanRemoval en Baeldung
    private List<ProductInCart> selectedProducts;

    public boolean hasProduct(Product productToAdd) {
        return selectedProducts.stream()
                .anyMatch(productInCart -> productInCart.getProduct().equals(productToAdd));  // todo: ojo, probar si esto realmente funciona o necesita un equals con los atributos que no cambian (id solo probablemente?)
    }

    public void addMoreUnitsOfProduct(long unitsRequested, Product productToAdd) {
        validateProductStockMeetsUnitsRequested(productToAdd, unitsRequested);
        
        ProductInCart productInCart = selectedProducts.stream()
                .filter(selectedProduct -> selectedProduct.getProduct().equals(productToAdd))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        
        productInCart.addUnits(unitsRequested);
    }
    
    private void validateProductStockMeetsUnitsRequested(Product product, long unitsRequested) {
        if (product.getStock() < unitsRequested) {
            throw new IllegalArgumentException("Stock insuficiente");
        }
    }

    public void addNewProduct(long unitsRequested, Product productToAdd) {
        validateProductIsNotInCart(productToAdd);
        validateProductStockMeetsUnitsRequested(productToAdd, unitsRequested);
        
        ProductInCart newProduct = ProductInCart.builder()
                .id(new ProductInCartId(productToAdd.getId(), ownerId))
                .product(productToAdd)
                .cart(this)
                .quantity(unitsRequested)
                .build();
        
        selectedProducts.add(newProduct);
        
        // todo: ojo, tambien buscar para que era el uso de @transactional y analizar donde tendría que aplicarlo
        // todo: tener en cuenta tema concurrencia? dejarlo para el final mepa
    }

    private void validateProductIsNotInCart(Product productToAdd) {
        if (hasProduct(productToAdd)) {
            throw new IllegalStateException("El producto ya existe");
        }
    }

    public void clear() {
        selectedProducts.clear();
    }

    public void removeProduct(long productIdToRemove) {
        // todo: valida que exista el producto. limpiarlo más adelante
        ProductInCart productInCartToRemove = selectedProducts.stream()
                .filter(productInCart -> productInCart.getProduct().getId() == productIdToRemove)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("El producto indicado no está en el carrito del usuario"));
        
        selectedProducts.remove(productInCartToRemove);
    }

    public void checkOut() {
        selectedProducts.forEach(ProductInCart::checkOut);
        selectedProducts.clear();
    }

    public CartDetailDto toDto() {
        List<ProductInCartDto> productInCartDtos = selectedProducts.stream().map(ProductInCart::toDto).toList();
        double cartPriceForDto = productInCartDtos.stream()
                .map(product -> product.getQuantity() * product.getPricePerUnit())
                .reduce(0d, Double::sum);
        
        return CartDetailDto.builder()
                .productsInCart(productInCartDtos)
                .cartPrice(cartPriceForDto)
                .build();
    }
}
