package com.backend.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;

    private LocalDate transactionDate;
    private LocalTime transactionTime;
    private TransactionType transactionType;
    private String transactionStatus;
    private Double transactionAmount;

    @OneToMany
    private List<Product> product = new ArrayList<Product>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
