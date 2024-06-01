package com.backend.ecommerce.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProductCategoryId implements Serializable {

    private String productId;
    private Integer categoryId;
}
