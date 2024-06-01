package com.backend.ecommerce.entity;

import com.backend.ecommerce.enums.PhoneType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long phoneNumber;
    private String areaCode;
    private String city;
    private String country;

    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;
}
