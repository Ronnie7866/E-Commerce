package com.backend.ecommerce.entity;

import com.backend.ecommerce.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
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

    private String status;
    private BigDecimal amount;
    private String notes;
    private BigDecimal transactionFee;

    @ElementCollection
    private List<String> productIds = new ArrayList<>();

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "bank_detail_id")
    private UserBankDetails bankDetail;


}
