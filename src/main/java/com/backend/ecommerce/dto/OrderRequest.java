package com.backend.ecommerce.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long userId;
    private List<OrderProductRequest> orderProducts;
}
