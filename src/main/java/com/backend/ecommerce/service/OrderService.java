package com.backend.ecommerce.service;

import com.backend.ecommerce.dto.OrderProductRequest;
import com.backend.ecommerce.dto.OrderRequest;
import com.backend.ecommerce.entity.*;
import com.backend.ecommerce.enums.OrderStatus;
import com.backend.ecommerce.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderProductsRepository orderProductsRepository;


    public void createOrder(OrderRequest orderRequest) {
        // Retrieve user
        User user = userRepository.findById(orderRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        // Create Order entity
        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PENDING);

        // Save order to get its ID
        order = orderRepository.save(order);

        // Create OrderProducts entities and establish mappings
        for (OrderProductRequest productRequest : orderRequest.getOrderProducts()) {
            Product product = productRepository.findById(productRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

            // Create OrderProduct entity
            OrderProducts orderProducts = new OrderProducts();
            orderProducts.setOrder(order);
            orderProducts.setProductId(product.getId());
            orderProducts.setQuantity(productRequest.getQuantity());

            // Save OrderProducts entity
            orderProductsRepository.save(orderProducts);

            // Add OrderProducts entity to order's list of order Products
            order.getOrderProducts().add(orderProducts);
        }

        // update the order
        orderRepository.save(order);
    }


    public Order getOrder(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
