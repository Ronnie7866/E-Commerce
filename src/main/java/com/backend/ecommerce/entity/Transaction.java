package com.backend.ecommerce.entity;

import com.backend.ecommerce.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate transactionDate;
    private LocalTime transactionTime;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String transactionStatus;
    private Double transactionAmount;

    @ElementCollection
    private List<String> productIds = new ArrayList<>();

    @ManyToOne
    private User user;

    @ManyToOne
    private Order order;
}
