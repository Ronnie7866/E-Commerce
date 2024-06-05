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


    public Order checkout(Long userId, List<Long> orderProductsIds, TransactionType transactionType, BigDecimal transactionAmount) {
        // Fetch the user
        System.out.println("Fetching user with ID: " + userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        System.out.println("User found: " + user);

        // Fetch and validate order products
        System.out.println("Fetching order products with IDs: " + orderProductsIds);
        List<OrderProducts> orderProducts = orderProductsRepository.findAllById(orderProductsIds);
        if (orderProducts.isEmpty()) {
            System.out.println("No order products found for IDs: " + orderProductsIds);
            throw new RuntimeException("Order products not found");
        }
        if (orderProducts.size() != orderProductsIds.size()) {
            System.out.println("Some order products not found. Found: " + orderProducts);
            throw new RuntimeException("Some order products not found");
        }
        System.out.println("Order products found: " + orderProducts);

        // Create the order
        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderProducts(orderProducts);

        // Create and save the transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setTransactionAmount(transactionAmount);
        transaction.setUser(user);
        transaction.setOrder(order); // Set the relationship
        transactionRepository.save(transaction);
        System.out.println("Transaction saved: " + transaction);

        // Add transaction to order
        order.setTransaction(transaction);

        // Set the order in each orderProduct
        for (OrderProducts orderProduct : orderProducts) {
            orderProduct.setOrder(order);
        }

        // Save the order
        orderRepository.save(order);
        System.out.println("Order saved: " + order);

        return order;
    }
}