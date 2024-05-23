package com.backend.ecommerce.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long number;
    private String areaCode;
    private String city;
    private String country;
    PhoneType phoneType;
    @ManyToOne
    private User user;
}
