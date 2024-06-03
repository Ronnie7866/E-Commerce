//package com.backend.ecommerce.controllers;
//
//import com.backend.ecommerce.dto.CheckoutRequest;
//import com.backend.ecommerce.entity.Order;
//import com.backend.ecommerce.service.CheckoutService;
//import com.backend.ecommerce.service.OrderService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("api/orders")
//@AllArgsConstructor
//public class OrderController {
//
//    private final OrderService orderService;
//    private final CheckoutService checkoutService;
//
//    @PostMapping("/checkout")
//    public ResponseEntity<String> createOrder(@RequestBody CheckoutRequest checkoutRequest) {
//        Order order = checkoutService.checkout(
//                checkoutRequest.getUser(),
//                checkoutRequest.getOrderProductsList(),
//                checkoutRequest.getTransaction()
//        );
//        return ResponseEntity.ok("Checkout successful ! Order ID : " + order.getId());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Order> getOrder(@PathVariable Integer id) {
//        Order order = orderService.getOrder(id);
//        return ResponseEntity.ok(order);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Order>> getAllOrders() {
//        List<Order> orders = orderService.getAllOrders();
//        return ResponseEntity.ok(orders);
//    }
//}
