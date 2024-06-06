package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.*;
import com.backend.ecommerce.repository.CartProductsRepository;
import com.backend.ecommerce.repository.CartRepository;
import com.backend.ecommerce.repository.ProductRepository;
import com.backend.ecommerce.repository.UserRepository;
import com.backend.ecommerce.dto.CartItemsDTO;
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
    private CartProductsRepository cartProductsRepository;

    /** The following code either creates a new cart with new cartItem
     * or creates a new cartItem in the cart, if product was not already in the cart
     * or updates the quantity of the product if it was already present in the cart
     **/
    public String addProductToCart(Long userId, String productId, Integer quantity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));


        Cart cart = user.getCart();

        CartProducts cartProducts;

        //FLOWCHART
        //                                                       /---- Exists ---> Update Its
        //                /--> Exists --------> product exists? {                   Quantity --->\
        //               /                                       \                                \
        // cart exists? {                                   Doesn't exist                          X ->Save Cart
        //               \                                         \                              /
        //                \--> Doesn't exist -> ----------------------> Create New item  ------->/
        //                                                                & add to cart

        Optional<CartProducts> existingCartItemOpt = Optional.empty();

        //if Cart is present then try finding if the product being added is already in the cart or not
        if(!Objects.isNull(cart)) {
            existingCartItemOpt = cartProductsRepository.findByCartIdAndProductId(cart.getId(), productId);
        }
        //if cart is not present then create new cart
        else{
            cart = new Cart();
            cart.setUser(user);
        }

        //if product is present then change its quantity to new one
        if (existingCartItemOpt.isPresent()) {
            cartProducts = existingCartItemOpt.get();
            cartProducts.setQuantity(quantity);
        }
        //if product is not present, or it's a new cart then create new item
        else {
            cartProducts = new CartProducts();
            cartProducts.setCart(cart);
            cartProducts.setProductId(productId);
            cartProducts.setQuantity(quantity);
        }

        CartProducts savedCartProducts = cartProductsRepository.save(cartProducts);
        return "Done";
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).get();
    }

    public List<CartItemsDTO> getCartItemsByUserId(Long userId) {
        Optional<Cart> cartOpt = cartRepository.findByUserId(userId);
        if(cartOpt.isEmpty()) {
            throw new RuntimeException("Not Present");
        }
        Long cartId = cartOpt.get().getId();
        List<CartProducts> cartProductsList = cartProductsRepository.findAllByCartId(cartId);
        return cartProductsList.stream()
                .map(CartItemsDTO::new).toList();
//        return null;
        //findAllByCart(cart);
    }
}
