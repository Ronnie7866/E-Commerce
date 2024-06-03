package com.backend.ecommerce.dto;


import com.backend.ecommerce.entity.OrderProducts;
import com.backend.ecommerce.entity.Transaction;
import com.backend.ecommerce.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckoutRequest {

    private User user;
    private List<Transaction> transaction;
    private List<OrderProducts> orderProductsList;
}
