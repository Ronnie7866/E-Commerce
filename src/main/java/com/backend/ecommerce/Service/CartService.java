package com.backend.ecommerce.Service;

import com.backend.ecommerce.Entity.*;
import com.backend.ecommerce.Repository.CartItemRepository;
import com.backend.ecommerce.Repository.CartRepository;
import com.backend.ecommerce.Repository.ProductRepository;
import com.backend.ecommerce.Repository.UserRepository;
import com.backend.ecommerce.dto.CartItemsDto;
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

    /** The following code either creates a new cart with new cartItem
     * or creates a new cartItem in the cart, if product was not already in the cart
     * or updates the quantity of the product if it was already present in the cart
     **/
    public String addProductToCart(Long userId, Long productId, Integer quantity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        //System.out.println("Finished existing check");
        //System.out.println(user);

        Cart cart = user.getCart();

        CartItem cartItem;

        //FLOWCHART
        //                                                       /---- Exists ---> Update Its
        //                /--> Exists --------> product exists? {                   Quantity --->\
        //               /                                       \                                \
        // cart exists? {                                   Doesn't exist                          X ->Save Cart
        //               \                                         \                              /
        //                \--> Doesn't exist -> ----------------------> Create New item  ------->/
        //                                                                & add to cart

        Optional<CartItem> existingCartItemOpt = Optional.empty();

        //if Cart is present then try finding if the product being added is already in the cart or not
        if(!Objects.isNull(cart)) {
            System.out.println("cart is not empty so finding if product present");
            existingCartItemOpt = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);
        }

        //if cart is not present then create new cart
        else{
            System.out.println("Cart not present. Creating new cart, setting user");
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }

        //if product is present then change its quantity to new one
        if (existingCartItemOpt.isPresent()) {
            System.out.println("product present, changing quanitity");
            cartItem = existingCartItemOpt.get();
            cartItem.setQuantity(quantity);
        }
        //if product is not present, or it's a new cart then create new item
        else {
            System.out.println("Product not present, creating cart item and inserting");
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }

        userRepository.save(user);
        cartItemRepository.save(cartItem);

//        System.out.println(user.getId());
//        System.out.println(cart.getId());
//        System.out.println(cartItem.getId());

//        CartItem savedCartItem = cartItemRepository.save(cartItem);
//        System.out.println("savedCartItem : " + savedCartItem);

//        System.out.println("User: " + user + "\n" + "Cart: " + cart + "\n" + "CartItem: " + cartItem);

        return "Done";
    }

//    public List<Cart> getAllCarts() {
//        return cartRepository.findAll();
//    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).get();
    }

    public List<CartItemsDto> getCartItemsByUserId(Long userId) {
        Optional<Cart> cartOpt = cartRepository.findByUserId(userId);
        if(cartOpt.isEmpty()) {
            throw new RuntimeException("Not Present");
        }
        Long cartId = cartOpt.get().getId();
        List<CartItem> cartItemList = cartItemRepository.findAllByCartId(cartId);
        return cartItemList.stream()
                .map(CartItemsDto::new).toList();
//        return null;
        //findAllByCart(cart);
    }
}
