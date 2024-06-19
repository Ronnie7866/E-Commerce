package com.backend.ecommerce.implementation;

import com.backend.ecommerce.dto.OrderProductRequest;
import com.backend.ecommerce.dto.OrderRequest;
import com.backend.ecommerce.entity.*;
import com.backend.ecommerce.enums.OrderStatus;
import com.backend.ecommerce.enums.TransactionType;
import com.backend.ecommerce.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImplementation implements com.backend.ecommerce.service.OrderService {

    private final OrderRepository orderRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderProductsRepository orderProductsRepository;
    private final CartRepository cartRepository;
    private final CartProductsRepository cartProductsRepository;
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void createOrder(OrderRequest orderRequest) {
        // Retrieve user
        User user = userRepository.findById(orderRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        // Create Order entity
        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PENDING);

        // Save order to get its ID
        order = orderRepository.save(order);

        // Create OrderProducts entities and establish mappings
        for (OrderProductRequest productRequest : orderRequest.getOrderProducts()) {
            Product product = productRepository.findById(productRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

            // Create OrderProduct entity
            OrderProducts orderProducts = new OrderProducts();
            orderProducts.setOrder(order);
            orderProducts.setProductId(product.getId());
            orderProducts.setQuantity(productRequest.getQuantity());

            // Save OrderProducts entity
            orderProductsRepository.save(orderProducts);

            // Add OrderProducts entity to order's list of order Products
            order.getOrderProducts().add(orderProducts);
        }

        // update the order
        orderRepository.save(order);
    }


    @Override
    public Order getOrder(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order convertCartToOrder(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        User user = cart.getUser();

        Order order = new Order();
        order.setUser(user);

        List<OrderProducts> orderProductsList = cart.getCartProducts().stream().map(cartProduct -> {
            OrderProducts orderProduct = new OrderProducts();
            orderProduct.setOrder(order);
            orderProduct.setProductId(cartProduct.getProductId());
            orderProduct.setQuantity(cartProduct.getQuantity());
            return orderProduct;
        }).collect(Collectors.toList());

        order.setOrderProducts(orderProductsList);

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order checkout(Long userId, Long cartId, TransactionType transactionType, BigDecimal transactionAmount) {
        // Fetch the user
        System.out.println("Fetching user with ID: " + userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        System.out.println("User found: " + user);

        // Fetch the cart
        System.out.println("Fetching cart with ID: " + cartId);
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart Not Found"));
        System.out.println("Cart found: " + cart);

        // Fetch and validate cart products
        List<CartProducts> cartProducts = cart.getCartProducts();
        if (cartProducts.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        System.out.println("Cart products found: " + cartProducts);

        // Convert cart products to order products
        List<OrderProducts> orderProducts = cartProducts.stream().map(cartProduct -> {
            OrderProducts orderProduct = new OrderProducts();
            orderProduct.setProductId(cartProduct.getProductId());
            orderProduct.setQuantity(cartProduct.getQuantity());
            return orderProduct;
        }).collect(Collectors.toList());

        // Create the order
        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderProducts(orderProducts);

        // Create and save the transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setTransactionAmount(transactionAmount);
        transaction.setUser(user);
        transaction.setOrder(order); // Set the relationship
        order.setTransaction(transaction);
        System.out.println("Transaction created: " + transaction);

        // Save the order
        orderRepository.save(order);
        System.out.println("Order saved: " + order);

        // Save the transaction
        transactionRepository.save(transaction);
        System.out.println("Transaction saved: " + transaction);

//        // Add transaction to order
//        order.setTransaction(transaction);
//        orderRepository.save(order);  // Ensure the transaction is associated with the order
//        System.out.println("Order updated with transaction: " + order);

        // Set the order in each orderProduct
        for (OrderProducts orderProduct : orderProducts) {
            orderProduct.setOrder(order);
        }

        // Ensure the deletion of cart products
        System.out.println("ye hai cart Id "+cartId);
        cartProductsRepository.deleteAll(cartProducts);
        System.out.println("Cart products deleted individually");

        // Flush and clear the EntityManager to force execution
        entityManager.flush();
        entityManager.clear();

        // Delete the cart
        cartRepository.deleteById(cartId);
        System.out.println("Cart deleted");

        return order;
    }
}
