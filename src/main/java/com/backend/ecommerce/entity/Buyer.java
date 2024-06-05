package com.backend.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer buyer_id;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private String deviceToken;

    @ManyToOne
    private Cart cart;

    @JsonIgnore
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> address;

//    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Order> order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
