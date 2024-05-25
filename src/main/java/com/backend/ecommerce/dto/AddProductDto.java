package com.backend.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class AddProductDto {
//    private String name;
//    private Double price;
//    private String description;
//    private List<Long> categoryIds;
//}


public record AddProductDto(String name, BigDecimal price, String description, List<Long> categoryIds) { }