package com.backend.ecommerce.entity;


import com.backend.ecommerce.enums.AvailabilityStatus;
import com.backend.ecommerce.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private LocalDateTime orderDate;
//    private LocalDateTime updatedAt;

//    @Enumerated(EnumType.STRING)
//    private AvailabilityStatus availabilityStatus;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Transaction transaction;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderProducts> orderProducts = new ArrayList<>();

//    @ManyToOne
//    private Buyer buyer;


    @CreationTimestamp
    @Column(name = "createdat", nullable = false)
    private LocalDateTime createdat;

    @UpdateTimestamp
    @Column(name = "modifiedat", nullable = false)
    private LocalDateTime modifiedate;

//    @Transient
//    private Long userId = user.getId();
//
//    @Transient
//    private Long transactionId = transaction.getId();
}
