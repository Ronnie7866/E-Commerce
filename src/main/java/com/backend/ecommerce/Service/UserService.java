package com.backend.ecommerce.Service;

import com.backend.ecommerce.Entity.Cart;
import com.backend.ecommerce.Entity.User;
import com.backend.ecommerce.Repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        userRepository.save(user);
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        User newUser = userRepository.findById(user.getId()).get();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        return userRepository.save(newUser);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }


    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
