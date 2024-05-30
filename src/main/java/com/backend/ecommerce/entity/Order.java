package com.backend.ecommerce.entity;


import com.backend.ecommerce.entity.Enum.Status;
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

    @OneToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Transaction transaction;

    @Enumerated(EnumType.STRING)
    private Status status;
}
