//package com.backend.ecommerce.service;
//
//import com.backend.ecommerce.entity.Enum.Status;
//import com.backend.ecommerce.entity.Order;
//import com.backend.ecommerce.entity.Transaction;
//import com.backend.ecommerce.entity.User;
//import com.backend.ecommerce.repository.OrderRepository;
//import com.backend.ecommerce.repository.TransactionRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@AllArgsConstructor
//public class OrderService {
//
//    private final OrderRepository orderRepository;
//    private final UserService userService;
//    private final TransactionService transactionService;
//    private final TransactionRepository transactionRepository;
//
//    public Order createOrder(Long buyerId, Long sellerId) {
//        // create Order
//        Order order = new Order();
//        order.setBuyerId(buyerId);
//        order.setSellerId(sellerId);
//        order.setOrderDate(LocalDateTime.now());
//        order.setStatus(Status.CREATED);
//
//        // set transaction associated with this order
//        Transaction transaction = new Transaction();
//        transaction.setOrder(order);
//        transactionRepository.save(transaction);
//        order.setTransaction(transaction);
//
//        return orderRepository.save(order);
//    }
//}
