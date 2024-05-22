package com.backend.ecommerce.Entity;

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

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //new
    @JoinColumn(name = "cart_id")
    private Cart cart;


    //    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

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
