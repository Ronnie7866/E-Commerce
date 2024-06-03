package com.backend.ecommerce.entity;

import com.backend.ecommerce.enums.AvailabilityStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private String id;
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

    private List<Long> categoryIds = new ArrayList<>();
    private Long transactionId;

}

