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
import java.util.Optional;

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
        System.out.println("Finished existing check");
        System.out.println(user);

        Cart cart = user.getCart();

        CartItem cartItem;

        //The following code either creates a new cart with new cartItem
        //or creates a new cartItem in the cart, if product was not already in the cart
        //or updates the quantity of the product if it was already present in the cart

        Optional<CartItem> existingCartItemOpt = Optional.empty();

        if(!Objects.isNull(cart)) {
            existingCartItemOpt = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);
        }
        if (existingCartItemOpt.isPresent()) {
            cartItem = existingCartItemOpt.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        else {
            cart = new Cart();
            cart.setUser(user);
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        System.out.println("savedCartItem : " + savedCartItem);

        return "Done";
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).get();
    }
}
