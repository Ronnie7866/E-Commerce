
package com.backend.ecommerce.service;

import com.backend.ecommerce.dto.CustomModelMapper;
import com.backend.ecommerce.dto.UserDTO;
import com.backend.ecommerce.exception.DuplicateEntryException;
import com.backend.ecommerce.exception.ResourceNotFoundException;
import com.backend.ecommerce.repository.CartItemRepository;
import com.backend.ecommerce.entity.User;
import com.backend.ecommerce.repository.CartRepository;
import com.backend.ecommerce.repository.ProductRepository;
import com.backend.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomModelMapper customModelMapper;


    public UserDTO createUser(UserDTO userDTO) {
        User user = customModelMapper.reverse(userDTO);
        if (isCustomerExists(user.getId()) || isDuplicate(userDTO)) {
            throw new DuplicateEntryException(userDTO.email());
        }
        return customModelMapper.apply(user);
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with this id " + id + " not found"));
        return customModelMapper.apply(user);
    }

    public List<UserDTO> getAllUsers() {
//        List<UserDTO> userDTOList = new ArrayList<>();
//        List<User> userList = userRepository.findAll();
//        for (User user : userList) {
//            UserDTO userDTO = customModelMapper.apply(user);
//            userDTOList.add(userDTO);
//        }
//        return userDTOList;
        return userRepository.findAll().stream().map(customModelMapper).toList();
    }

    //TODO take user details from DTO
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        isCustomerExists(userId);
        isDuplicate(userDTO);
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with this id : " + userId));
        User updatedValues = customModelMapper.reverse(userDTO);

        BeanUtils.copyProperties(updatedValues, existingUser);
        return customModelMapper.apply(userRepository.save(existingUser));
    }

    public boolean verifyUser(Long userId, String firstName, String lastName, Long phoneNumber) {
        isCustomerExists(userId);
        return userRepository.existsByIdAndFirstNameAndLastNameAndDefaultPhoneNumber(userId, firstName, lastName, phoneNumber);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public UserDTO getUserByEmail(String email) {
        return customModelMapper.apply(userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with the " + email + " not found")));
    }

    public UserDTO register(User user) {
        user.setPassword(user.getPassword());
        user.setEmail(user.getEmail());
        return customModelMapper.apply(userRepository.save(user));
    }

    public UserDTO login(String email, String password) {
        User user = userRepository.findByEmail(email).get();
        if (user.getPassword().equals(password)) {
            return customModelMapper.apply(user);
        } else {
            throw new RuntimeException("Wrong Password");
        }
    }

    private boolean isCustomerExists(Long id) {
        if(!userRepository.existsById(id)){
            return true;
        }
        throw new ResourceNotFoundException("Customer Not Found");
    }

    private boolean isDuplicate(UserDTO userDTO) {
        if (userDTO == null) {
            throw new RuntimeException("UserDTO is null");
        }
        if (Objects.nonNull(userDTO.defaultPhoneNumber()) && userRepository.existsByDefaultPhoneNumber(userDTO.defaultPhoneNumber())) {
            return true;
        }
        return Objects.nonNull(userDTO.email()) && userRepository.existsByEmail(userDTO.email());
    }

}
