package com.backend.ecommerce.dto;

import com.backend.ecommerce.entity.Product;


public record CartProductDTO ( Long id,
         Product product,
         Integer quantity,
         String availabilityStatus) {
}