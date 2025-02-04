package com.backend.ecommerce.Controller;

import com.backend.ecommerce.Entity.Cart;
import com.backend.ecommerce.Entity.CartItem;
import com.backend.ecommerce.Repository.CartItemRepository;
import com.backend.ecommerce.Repository.CartRepository;
import com.backend.ecommerce.Service.CartService;
import com.backend.ecommerce.dto.CartItemsDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

//    @GetMapping
//    public List<Cart> getAllCarts() {
//        return cartService.getAllCarts();
//    }

    @GetMapping("/user/{userId}")
    public List<CartItemsDto> getCartItemsByUserId(@PathVariable Long userId) {
        return cartService.getCartItemsByUserId(userId);
    }

    @PostMapping("/{userId}")
    public String addProductToCart(@PathVariable Long userId, @RequestParam Long productId, @RequestParam Integer quantity){
        return cartService.addProductToCart(userId, productId, quantity);
    }

    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable Long cartId){
        return cartService.getCart(cartId);
    }
}
