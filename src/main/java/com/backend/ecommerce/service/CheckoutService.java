//package com.backend.ecommerce.service;
//
//
//import com.backend.ecommerce.entity.Order;
//import com.backend.ecommerce.entity.OrderProducts;
//import com.backend.ecommerce.entity.Transaction;
//import com.backend.ecommerce.entity.User;
//import com.backend.ecommerce.enums.AvailabilityStatus;
//import com.backend.ecommerce.enums.OrderStatus;
//import com.backend.ecommerce.repository.OrderRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class CheckoutService {
//
//    private final OrderRepository orderRepository;
//
//    public CheckoutService(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
//
//    public Order checkout(User user, List<OrderProducts> orderProducts, List<Transaction> transactions) {
//
//        Order order = new Order();
//
//        order.setOrderDate(LocalDateTime.now());
//        order.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
//        order.setOrderStatus(OrderStatus.PENDING);
//        order.setUser(user);
//        order.setOrderProducts(orderProducts);
//
//        for (Transaction transaction : transactions) {
//            transaction.setOrder(order);
//            order.getTransactions().add(transaction);
//        }
//
//        for (OrderProducts orderProduct : orderProducts) {
//            orderProduct.setOrder(order);
//        }
//
//
//        return orderRepository.save(order);
//    }
//}
