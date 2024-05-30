package com.backend.ecommerce.entity;


import jakarta.persistence.*;

@Entity
public class OrderedProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long orderId;
    private Long productId;
    private Long quantity;

    @ManyToOne
    private Order order;
}
