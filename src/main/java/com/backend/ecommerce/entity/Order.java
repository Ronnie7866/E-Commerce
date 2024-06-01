package com.backend.ecommerce.entity;


import com.backend.ecommerce.enums.AvailabilityStatus;
import com.backend.ecommerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long buyerId;
    private Long sellerId;
    private LocalDateTime orderDate;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Transaction transaction;

    @Transient
    private Long userId;

    @Transient
    private Long transactionId;
}
