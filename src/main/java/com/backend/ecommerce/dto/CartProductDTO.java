package com.backend.ecommerce.dto;

import lombok.Data;


public record CartProductDTO ( Long id,
         String productId,
         Integer quantity,
         String availabilityStatus) {
}