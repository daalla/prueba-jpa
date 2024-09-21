package dev.dallavalle.pruebajpa.presentation;

import dev.dallavalle.pruebajpa.application.CartService;
import dev.dallavalle.pruebajpa.domain.entities.Cart;
import dev.dallavalle.pruebajpa.domain.entities.User;
import dev.dallavalle.pruebajpa.presentation.dtos.CartDetailDto;
import dev.dallavalle.pruebajpa.presentation.dtos.CartRequestDto;
import dev.dallavalle.pruebajpa.presentation.dtos.CartResponseDto;
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
        
        cartService.addProductToCart(cartRequestDto, requestingUser);  // todo: OJO, NO DEBERIA SACAR STOCK POR PONER EN CARRITO, SINO SOLO VALIDAR QUE ESTE EL STOCK Y RECIEN EN CHECKOUT SACARLO DE STOCK 
        return new CartResponseDto("Producto agregado");
    }
    
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public CartResponseDto clearUserCart() {
        // todo: volar esto cuando se agregue spring security
        User requestingUser = new User();
        requestingUser.setId(1);
        
        cartService.clearUserCart(requestingUser);
        
        return new CartResponseDto("Carrito vaciado");
    }
    
    @DeleteMapping(path = "/products/{productIdToRemove}")
    @ResponseStatus(HttpStatus.OK)
    public CartResponseDto removeProductFromUserCart(@PathVariable long productIdToRemove) {
        // todo: volar esto cuando se agregue spring security
        User requestingUser = new User();
        requestingUser.setId(1);
        
        cartService.removeProductFromUserCart(productIdToRemove, requestingUser);
        
        return new CartResponseDto("Producto removido");
    }
    
    @PostMapping(path = "/checkout")
    @ResponseStatus(HttpStatus.OK)
    public CartResponseDto checkoutUserCart() {
        // todo: volar esto cuando se agregue spring security
        User requestingUser = new User();
        requestingUser.setId(1);
        
        cartService.checkoutUserCart(requestingUser);
        
        return new CartResponseDto("Carrito comprado");
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CartDetailDto getCartDetail() {
        // todo: volar esto cuando se agregue spring security
        User requestingUser = new User();
        requestingUser.setId(1);
        
        Cart userCart = cartService.getUserCart(requestingUser);
        
        return userCart.toDto();
    }
}
