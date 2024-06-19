package com.backend.ecommerce.Controller;

import com.backend.ecommerce.Entity.Order;
import com.backend.ecommerce.Entity.TransactionType;
import com.backend.ecommerce.Service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("checkout")
    public String checkout(@RequestParam Long userId,
                          @RequestParam Long cartId,
                          @RequestParam BigDecimal transactionAmount) {
        return orderService.checkout(userId, cartId, transactionAmount);
    }

//    @PostMapping
//    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//        return ResponseEntity.ok(orderService.createOrder(order));
//    }
}
