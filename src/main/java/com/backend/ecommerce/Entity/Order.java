package com.backend.ecommerce.Entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "order")
    private Transaction transaction;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> orderProducts = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "createdat", nullable = false)
    private LocalDateTime createdat;

    @UpdateTimestamp
    @Column(name = "modifiedat", nullable = false)
    private LocalDateTime modifiedat;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", user=" + user.getId() +
                ", transaction=" + transaction.getId() +
                ", orderProducts=" + orderProducts.stream().map(OrderItem::getId).toList() +
                ", createdat=" + createdat +
                ", modifiedat=" + modifiedat +
                '}';
    }
}
