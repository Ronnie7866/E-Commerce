package com.backend.ecommerce.service;


import com.backend.ecommerce.entity.Order;
import com.backend.ecommerce.entity.OrderProducts;
import com.backend.ecommerce.entity.Transaction;
import com.backend.ecommerce.entity.User;
import com.backend.ecommerce.enums.AvailabilityStatus;
import com.backend.ecommerce.enums.OrderStatus;
import com.backend.ecommerce.enums.TransactionType;
import com.backend.ecommerce.repository.OrderProductsRepository;
import com.backend.ecommerce.repository.OrderRepository;
import com.backend.ecommerce.repository.TransactionRepository;
import com.backend.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CheckoutService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderProductsRepository orderProductsRepository;
    private final TransactionRepository transactionRepository;

//    public CheckoutService(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

    public Order checkout(Long userId, List<Long> orderProductsId, TransactionType transactionType, BigDecimal transactionAmount) {

        Order order = new Order();

//        order.setOrderDate(LocalDateTime.now());
//        order.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        order.setOrderStatus(OrderStatus.PENDING);

        User user = userRepository.findById(userId).get();
        order.setUser(user);
        
        List<OrderProducts> orderProductsList = orderProductsRepository.findAllById(orderProductsId);
        order.setOrderProducts(orderProductsList);

        Transaction t = new Transaction();
        t.setTransactionType(transactionType);
        t.setTransactionAmount(transactionAmount);
        t.setUser(user);

        //GHAPLA

        order.setTransaction(t);

        Order savedOrder = orderRepository.save(order);

        t.setOrder(savedOrder);
        transactionRepository.save(t); //todo
        //

        return savedOrder;
    }
}
