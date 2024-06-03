package com.backend.ecommerce.entity;

import com.backend.ecommerce.enums.AvailabilityStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //new
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "product_id")
    private String productId;

    @Transient
    private Product product;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", cart=" + "Cart Value Omitted" +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
