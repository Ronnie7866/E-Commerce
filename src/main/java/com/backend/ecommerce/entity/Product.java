package com.backend.ecommerce.entity;

import com.backend.ecommerce.enums.AvailabilityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private Integer stockQuantity;
    private BigDecimal averageRating;
    private LocalDateTime dateAdded;
    private LocalDateTime dateUpdated;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availability;

    @ManyToMany(mappedBy = "products")
    private List<Category> category = new ArrayList<>();



//    @OneToMany
//    private List<Long> productImageIds = new ArrayList<>();
}

