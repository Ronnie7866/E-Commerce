package com.backend.ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonIgnore
    @OneToOne
    private User user;

//    @JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true) //removed , cascade = CascadeType.ALL
    private Set<CartItem> cartItems = new HashSet<>();

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", cartItems=" + " cartItems value omitted " +
                '}';
    }
}
