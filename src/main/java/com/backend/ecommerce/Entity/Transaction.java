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

    private LocalDate transaction_date;
    private LocalTime transaction_time;
    private String transaction_type;
    private String transaction_status;
    private Double transaction_amount;

    @OneToMany
    private List<Product> product = new ArrayList<Product>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
