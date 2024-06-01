package com.backend.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_category")
@IdClass(ProductCategoryId.class)
@Data
public class ProductCategory {

    @Id
    private Long productId;
    @Id
    private Long categoryId;
}
