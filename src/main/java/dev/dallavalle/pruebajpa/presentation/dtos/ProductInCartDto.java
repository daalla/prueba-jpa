package dev.dallavalle.pruebajpa.presentation.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductInCartDto {
    long productId;
    String name;
    long quantity;
    double pricePerUnit;
}
