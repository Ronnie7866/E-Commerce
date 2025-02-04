
package com.backend.ecommerce.Service;

//import com.backend.ecommerce.Entity.Cart;
import com.backend.ecommerce.Repository.CartItemRepository;
import com.backend.ecommerce.Entity.User;
import com.backend.ecommerce.Repository.CartRepository;
import com.backend.ecommerce.Repository.ProductRepository;
import com.backend.ecommerce.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;


    public User createUser(User user) {
        userRepository.save(user);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User Not Found with ID: " + user.getId()));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public User register(User user) {
        user.setPassword(user.getPassword());
        user.setEmail(user.getEmail());
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).get();
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new RuntimeException("Wrong Password");
        }
    }
}
