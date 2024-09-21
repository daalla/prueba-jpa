package dev.dallavalle.pruebajpa.presentation.dtos;

import lombok.Value;

@Value
public class CartRequestDto {
    long productId;
    long quantity;
}
