package com.backend.ecommerce.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Getter
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    public void addProductToCart(Product product) {
        if (this.cart == null) {
            this.cart = new Cart();
            this.cart.setUser(this);
        }
        this.cart.addProduct(product);
    }
}
