package dev.dallavalle.pruebajpa.presentation;

import dev.dallavalle.pruebajpa.application.CartService;
import dev.dallavalle.pruebajpa.infrastructure.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cart", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartController {
    private final CartService cartService;
    
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
    @PostMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CartResponseDto addProductToCart(@RequestBody CartRequestDto cartRequestDto) {
        // todo: remover cuando agrege spring security
        User requestingUser = new User();
        requestingUser.setId(1);
        
        cartService.addProductToCart(cartRequestDto, requestingUser);
        return new CartResponseDto("Producto agregado");
    }
    
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public CartResponseDto emptyShoppingCart() {
        User requestingUser = new User();
        requestingUser.setId(1);
    }
}
