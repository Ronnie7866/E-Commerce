package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.Order;
import com.backend.ecommerce.entity.Transaction;
import com.backend.ecommerce.entity.User;
import com.backend.ecommerce.repository.OrderRepository;
import com.backend.ecommerce.repository.TransactionRepository;
import com.backend.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public Order createOrder(Order order) {
        User user = userRepository.findById(order.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Transaction transaction = transactionRepository.findById(order.getTransactionId()).orElseThrow(() -> new RuntimeException("Transaction not found"));
        order.setUser(user);
        order.setTransaction(transaction);
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order, Integer id) {
        Order oldOrder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        // update the order details
        oldOrder.setBuyerId(order.getBuyerId());
        oldOrder.setSellerId(order.getSellerId());
        oldOrder.setOrderDate(order.getOrderDate());
        oldOrder.setOrderStatus(order.getOrderStatus());
        oldOrder.setUpdatedAt(order.getUpdatedAt());
        oldOrder.setAvailabilityStatus(order.getAvailabilityStatus());

        // fetch and set the user
        if (order.getUserId() != null) {
            User user = userRepository.findById(order.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
            oldOrder.setUser(user);
        }

        // fetch and set the user
        if (order.getTransactionId() != null) {
            Transaction transaction = transactionRepository.findById(order.getTransactionId()).orElseThrow(() -> new RuntimeException("Transaction not found"));
            oldOrder.setTransaction(transaction);
        }
        return orderRepository.save(oldOrder);
    }

    public Order getOrder(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
