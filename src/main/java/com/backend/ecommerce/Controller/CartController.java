package com.backend.ecommerce.Controller;

import com.backend.ecommerce.Entity.Cart;
import com.backend.ecommerce.Service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    CartService cartService;

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }
    @PostMapping("/{userId}")
    public String addProductToCart(@PathVariable Long userId, @RequestParam Long productId, @RequestParam Integer quantity){
        return cartService.addProductToCart(userId, productId, quantity);
    }
}
