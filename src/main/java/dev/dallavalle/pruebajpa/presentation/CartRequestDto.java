package dev.dallavalle.pruebajpa.presentation;

import lombok.Value;

@Value
public class CartRequestDto {
    long productId;
    long quantity;
}
