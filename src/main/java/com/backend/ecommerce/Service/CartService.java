package com.backend.ecommerce.Service;

import com.backend.ecommerce.Entity.*;
import com.backend.ecommerce.Repository.CartItemRepository;
import com.backend.ecommerce.Repository.CartRepository;
import com.backend.ecommerce.Repository.ProductRepository;
import com.backend.ecommerce.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class CartService {

    private UserRepository userRepository;
    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private CartItemRepository cartItemRepository;

    public String addProductToCart(Long userId, Long productId, Integer quantity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = user.getCart();

        if(Objects.isNull(cart)){
            cart = new Cart();
            user.setCart(cart);
        }

        User u = userRepository.save(user);

        System.out.println(u);

        cart.setUser(u);

//        Cart c = cartRepository.save(cart);
//
//        System.out.println(c);

//        CartItem cartItem = new CartItem();
//        cartItem.setCart(cart);
//        cartItem.setProduct(product);
//        cartItem.setQuantity(quantity);
//
//        cart.getCartItems().add(cartItem);


//        cartItemRepository.save(cartItem);

        return "Done";
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
}
