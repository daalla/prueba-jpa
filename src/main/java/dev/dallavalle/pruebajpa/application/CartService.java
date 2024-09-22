package dev.dallavalle.pruebajpa.application;

import dev.dallavalle.pruebajpa.domain.entities.Cart;
import dev.dallavalle.pruebajpa.domain.entities.Product;
import dev.dallavalle.pruebajpa.domain.entities.User;
import dev.dallavalle.pruebajpa.domain.repositories.CartRepository;
import dev.dallavalle.pruebajpa.domain.repositories.ProductRepository;
import dev.dallavalle.pruebajpa.presentation.dtos.CartRequestDto;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public CartService(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    // todo: no olvidarme de los @Transaction. ver donde tendria que agregarlos.
    public void addProductToCart(CartRequestDto cartRequestDto, User requestingUser) {
        long productId = cartRequestDto.getProductId();
        long userId = requestingUser.getId();
        long unitsRequested = cartRequestDto.getQuantity();

        Product productToAdd = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);;
//        Cart userCart = cartRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Cart userCart = cartRepository.findByUser(requestingUser).orElseThrow(EntityNotFoundException::new);

        // todo: creo que esta lógica podría ir toda dentro de addProduct en la clase Cart, asi respeto mejor grasp/solid
        if (userCart.hasProduct(productToAdd)) {
            userCart.addMoreUnitsOfProduct(unitsRequested, productToAdd);
        } else {
            userCart.addNewProduct(unitsRequested, productToAdd);
        }
        
        cartRepository.save(userCart);
    }

    public void clearUserCart(User requestingUser) {
        Cart userCart = cartRepository.findById(requestingUser.getId()).orElseThrow(EntityNotFoundException::new);
        
        userCart.clear();
        
        cartRepository.save(userCart);
    }

    public void removeProductFromUserCart(long productIdToRemove, User requestingUser) {
        Cart userCart = cartRepository.findById(requestingUser.getId()).orElseThrow(EntityNotFoundException::new);
        
        userCart.removeProduct(productIdToRemove);
        
        cartRepository.save(userCart);
    }

    public void checkOutUserCart(User requestingUser) {
        Cart userCart = cartRepository.findById(requestingUser.getId()).orElseThrow(EntityNotFoundException::new);
        logger.info("Carrito pagado");
        userCart.checkOut();
        cartRepository.save(userCart);
    }

    public Cart getUserCart(User requestingUser) {
        return cartRepository.findById(requestingUser.getId()).orElseThrow(EntityNotFoundException::new);
    }
}
