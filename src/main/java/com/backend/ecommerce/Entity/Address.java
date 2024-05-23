package com.backend.ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long phoneNumber;
    private String areaCode;
    private String city;
    private String country;
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;
    @ManyToOne
    @JsonIgnore
    private User user;
}
