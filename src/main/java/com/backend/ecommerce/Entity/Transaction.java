//package com.backend.ecommerce.Entity;
//
//import jakarta.persistence.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class Transaction {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int transaction_id;
//    private String transaction_date;
//    private String transaction_time;
//    private String transaction_type;
//    private String transaction_status;
//    private String transaction_amount;
//    @OneToMany
//    private List<Product> product = new ArrayList<Product>();
//}
