package com.backend.ecommerce.entity;

import com.backend.ecommerce.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)     //TODO debit credit NEFT IMPS
    private TransactionType transactionType;

    private String transactionStatus; //TODO ENUM enum('pending', 'shipped', 'delivered', 'refunded') [default: 'pending']

    private BigDecimal transactionAmount;

//    @ElementCollection
//    private List<String> productIds = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "createdat", nullable = false)
    private LocalDateTime createdat;

    @UpdateTimestamp
    @Column(name = "modifiedat", nullable = false)
    private LocalDateTime modifiedat;

    @ManyToOne
    private User user;

    @OneToOne
    private Order order;
}
