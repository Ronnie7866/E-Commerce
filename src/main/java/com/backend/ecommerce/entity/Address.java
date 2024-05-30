package com.backend.ecommerce.entity;

import com.backend.ecommerce.entity.Enum.PhoneType;
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
}
