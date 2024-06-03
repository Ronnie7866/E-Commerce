package com.backend.ecommerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    private List<String> productIds = new ArrayList<>();

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String companyName;
    private String securityQuestion;
    private String securityAnswer;
    private String gstNumber;
}
