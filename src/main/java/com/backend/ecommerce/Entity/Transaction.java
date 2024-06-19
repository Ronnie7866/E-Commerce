package com.backend.ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.aspectj.weaver.ast.Or;
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
    private LocalDate transactionDate;
    private LocalTime transactionTime;
    private TransactionType transactionType;
    private String transactionStatus;
    private BigDecimal transactionAmount;

    @CreationTimestamp
    @Column(name = "createdat", nullable = false)
    private LocalDateTime createdat;

    @UpdateTimestamp
    @Column(name = "modifiedat", nullable = false)
    private LocalDateTime modifiedat;

    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToOne
    private Order order;


}
