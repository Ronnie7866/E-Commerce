package com.backend.ecommerce.Entity;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private String productDescription;
    private int productPrice;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Transaction transaction;
}
