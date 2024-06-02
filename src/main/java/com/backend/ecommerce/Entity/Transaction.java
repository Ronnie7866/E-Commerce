package com.backend.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

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
    private TransactionType transactionType;
    private String transactionStatus;
    private Double transactionAmount;

    @OneToMany
    private List<Product> product = new ArrayList<Product>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private Order order;


}
