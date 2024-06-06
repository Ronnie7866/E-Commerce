package com.backend.ecommerce.controllers;

import com.backend.ecommerce.dto.CheckoutRequest;
import com.backend.ecommerce.dto.OrderRequest;
import com.backend.ecommerce.entity.Order;
import com.backend.ecommerce.enums.TransactionType;
import com.backend.ecommerce.service.CheckoutService;
import com.backend.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CheckoutService checkoutService;

    @PostMapping("checkout")
    public Order checkout(@RequestParam Long userId,
                          @RequestParam Long cartId,
                          @RequestParam TransactionType transactionType,
                          @RequestParam BigDecimal transactionAmount) {
        return checkoutService.checkout(userId, cartId, transactionType, transactionAmount);
    }


    @PostMapping()
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        Order order = orderService.getOrder((int) id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> allOrders = orderService.getAllOrders();
        return ResponseEntity.ok(allOrders);
    }

    @PostMapping("/convert/{cartId}")
    public Order convertCartToOrder(@PathVariable Long cartId) {
        return orderService.convertCartToOrder(cartId);
    }
}

