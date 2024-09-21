package dev.dallavalle.pruebajpa.presentation.dtos;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CartDetailDto {
    List<ProductInCartDto> productsInCart;
    double cartPrice;
}
