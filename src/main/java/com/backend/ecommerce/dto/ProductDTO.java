package com.backend.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;


public record ProductDTO(String name
        , BigDecimal price
        , String description
        , List<Long> categoryIds) { }