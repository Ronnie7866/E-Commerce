package com.backend.ecommerce.Service;

import com.backend.ecommerce.Entity.*;
import com.backend.ecommerce.Repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final UserService userService;
    private final CartItemRepository cartItemRepository;
//    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;

//    public Order createOrder(Order order) {
//        User user = userService.getUserById(order.getUser().getId());
//        Transaction transaction = transactionService.getTransactionById(order.getTransaction().getId());
//        order.setUser(user);
//        order.setTransaction(transaction);
//        return orderRepository.save(order);
//    }

    @Transactional
    public String checkout(Long userId, Long cartId, BigDecimal transactionAmount) {
        // Fetch the user
//        System.out.println("Fetching user with ID: " + userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
//        System.out.println("User found: " + user);

        // Fetch the cart
//        System.out.println("Fetching cart with ID: " + cartId + "or from use: " + user.getCart().getId());
//        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart Not Found"));
        if(!cartRepository.existsById(cartId)){throw new RuntimeException("Cart Not Found");}
        //        System.out.println("Cart found: " + cart.getId());

        //todo cartProducts brought by its repository
        // i.e. jugad paani, refrain from this
        // problem: when getting from cart itself then giving recursion b/w cart and user
        //
        // Fetch and validate cart products
        // Set<CartItem> cartProducts = cart.getCartItems();

        List<CartItem> cartProducts = cartItemRepository.findAllByCartId(cartId);
        //----------------
        if (cartProducts.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        System.out.println("Cart products found: " + cartProducts.size());

        // Create the order
        Order order = new Order();
        order.setUser(user);
        order.setStatus(Status.PENDING);

        System.out.println("Created Order: " + "order.getId()");

        // Convert cart products to order products
        List<OrderItem> orderProducts = cartProducts.stream().map(cartProduct -> {
            OrderItem orderProduct = new OrderItem();
            orderProduct.setOrder(order);
            orderProduct.setProductId(cartProduct.getProduct().getId());
            orderProduct.setQuantity(cartProduct.getQuantity());
            return orderProduct;
        }).toList();

        System.out.println("Created Order Products " + "orderProducts.size()");


        order.setOrderProducts(orderProducts);

//        // Create and save the transaction
//        Transaction transaction = new Transaction();
//        transaction.setTransactionType(transactionType);
//        transaction.setTransactionAmount(transactionAmount);
//        transaction.setUser(user);
//        transaction.setOrder(order); // Set the relationship
//        System.out.println("Transaction saved: " + transaction);
//        transactionRepository.save(transaction);

        // Add transaction to order
//        order.setTransaction(transaction);

        // Save the order
        orderRepository.save(order);
        System.out.println("Order saved: " + order.getId());

        // Clear the cart (optional)
        Cart cart = cartRepository.findById(cartId).get();

        //NOT ABLE TO EVEN TOUCH cart OBJECT, else gives HashCode Recursion
        //however order and orderItems are inserted gracefully.

//        System.out.println(cart);
//        cartRepository.delete(cart);
//
//        cart.getCartItems().clear();

//        cartRepository.deleteById(cartId);
//        cartRepository.deleteByUserId(user.getId());
//        cartItemRepository.deleteAll(cartProducts);

//        System.out.println(cart);
//        System.out.println(cartProducts);
//        cartRepository.deleteById(cartId);
//        System.out.println("Cart and cart products deleted");

        return "order done";
    }
}
