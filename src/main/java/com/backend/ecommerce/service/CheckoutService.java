package com.backend.ecommerce.service;


import com.backend.ecommerce.entity.*;
import com.backend.ecommerce.enums.AvailabilityStatus;
import com.backend.ecommerce.enums.OrderStatus;
import com.backend.ecommerce.enums.TransactionType;
import com.backend.ecommerce.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CheckoutService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderProductsRepository orderProductsRepository;
    private final TransactionRepository transactionRepository;
    private final CartProductsRepository cartProductsRepository;
    private final CartRepository cartRepository;


    public Order checkout(Long userId, Long cartId, TransactionType transactionType, BigDecimal transactionAmount) {
        // Fetch the user
        System.out.println("Fetching user with ID: " + userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        System.out.println("User found: " + user);

        // Fetch the cart
        System.out.println("Fetching cart with ID: " + cartId);
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart Not Found"));
        System.out.println("Cart found: " + cart);

        // Fetch and validate cart products
        List<CartProducts> cartProducts = cart.getCartProducts();
        if (cartProducts.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        System.out.println("Cart products found: " + cartProducts);

        // Convert cart products to order products
        List<OrderProducts> orderProducts = cartProducts.stream().map(cartProduct -> {
            OrderProducts orderProduct = new OrderProducts();
            orderProduct.setProductId(cartProduct.getProductId());
            orderProduct.setQuantity(cartProduct.getQuantity());
            return orderProduct;
        }).collect(Collectors.toList());

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

        // Clear the cart (optional)
        cartProductsRepository.deleteAll(cartProducts);
        cartRepository.delete(cart);
        System.out.println("Cart and cart products deleted");

        return order;
    }
}