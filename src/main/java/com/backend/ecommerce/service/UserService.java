
package com.backend.ecommerce.service;

//import com.backend.ecommerce.Entity.Cart;
import com.backend.ecommerce.repository.CartItemRepository;
import com.backend.ecommerce.entity.User;
import com.backend.ecommerce.repository.CartRepository;
import com.backend.ecommerce.repository.ProductRepository;
import com.backend.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final PasswordEncoder passwordEncoder;


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

    //TODO take user details from DTO
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found with ID: " + user.getId()));

//        existingUser.setFirstName(user.getFirstName());
//        existingUser.setLastName(user.getLastName());
//        existingUser.setEmail(user.getEmail());
//        existingUser.setPassword(user.getPassword());

        //20 field
        //dto 10 field
        //dto 10 field | changed field 1,2,3,4,5,10

        //1
        //id se existing user -  10 field update - existing modified user save in db
        //return user

        //2
        //id se existing user - cehck on every field null/not null - update in db - return

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
