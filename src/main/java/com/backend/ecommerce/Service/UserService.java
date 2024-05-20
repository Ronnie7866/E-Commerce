//package com.backend.ecommerce.Service;
//
////import com.backend.ecommerce.Entity.Cart;
//import com.backend.ecommerce.Repository.CartItemRepository;
//import com.backend.ecommerce.Entity.User;
//import com.backend.ecommerce.Repository.CartRepository;
//import com.backend.ecommerce.Repository.ProductRepository;
//import com.backend.ecommerce.Repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class UserService {
//
//    private UserRepository userRepository;
//    private CartRepository cartRepository;
//    private ProductRepository productRepository;
//    private CartItemRepository cartItemRepository;
//
//    public User createUser(User user) {
////        Cart cart = cartRepository.save(new Cart());
////        user.setCarts(cart);
//        userRepository.save(user);
//        return userRepository.save(user);
//    }
//
//    public User getUserById(Long id) {
//        return userRepository.findById(id).get();
//    }
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public User updateUser(User user) {
//        User newUser = userRepository.findById(user.getId()).get();
//        newUser.setFirstName(user.getFirstName());
//        newUser.setLastName(user.getLastName());
//        newUser.setEmail(user.getEmail());
//        newUser.setPassword(user.getPassword());
//        return userRepository.save(newUser);
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//
//
//    public User getUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//}
