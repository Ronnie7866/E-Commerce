package com.backend.ecommerce.Service;

import com.backend.ecommerce.Entity.Order;
import com.backend.ecommerce.Entity.Transaction;
import com.backend.ecommerce.Entity.User;
import com.backend.ecommerce.Repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final TransactionService transactionService;

    public Order createOrder(Order order) {
        User user = userService.getUserById(order.getUser().getId());
        Transaction transaction = transactionService.getTransactionById(order.getTransaction().getId());
        order.setUser(user);
        order.setTransaction(transaction);
        return orderRepository.save(order);
    }
}
