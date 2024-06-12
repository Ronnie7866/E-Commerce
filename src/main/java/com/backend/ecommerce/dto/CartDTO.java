package com.backend.ecommerce.dto;

import lombok.Data;

import java.util.List;


public record CartDTO (Long id,
        Long userId,
        List<CartProductDTO> cartProducts) {
}
