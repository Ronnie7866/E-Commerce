package com.backend.ecommerce.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long buyerId;
    private Long sellerId;
    private LocalDateTime orderDate;
    private LocalDateTime updatedAt;

    @OneToOne
    private User user;

    @OneToOne(mappedBy = "order")
    private Transaction transaction;

    @Enumerated(EnumType.STRING)
    private Status status;
}
