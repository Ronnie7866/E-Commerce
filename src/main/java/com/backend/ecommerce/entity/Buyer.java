//package com.backend.ecommerce.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.List;
//
//@Entity
//@Data
//@Table(name = "buyers")
//public class Buyer {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String password;
//    private String defaultPhone;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Address> address;
//}
