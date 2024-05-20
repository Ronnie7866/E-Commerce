//package com.backend.ecommerce.Service;
//
//import com.backend.ecommerce.Entity.*;
//import com.backend.ecommerce.Repository.CartItemRepository;
//import com.backend.ecommerce.Repository.CartRepository;
//import com.backend.ecommerce.Repository.ProductRepository;
//import com.backend.ecommerce.Repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class CartService {
//
//    private UserRepository userRepository;
//    private CartRepository cartRepository;
//    private ProductRepository productRepository;
//    private CartItemRepository cartItemRepository;
//
//    public void addProductToCart(Long userId, Long productId, Integer quantity) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
//
//        Cart cart = user.getCarts().stream().findFirst().orElse(new Cart());
//        cart.setUser(user);
//        user.getCarts().add(cart);
//
//        CartItem cartItem = new CartItem();
//        cartItem.setCart(cart);
//        cartItem.setProduct(product);
//        cartItem.setQuantity(quantity);
//
//        cart.getCartItems().add(cartItem);
//
//        cartRepository.save(cart);
//        cartItemRepository.save(cartItem);
//    }
//}
