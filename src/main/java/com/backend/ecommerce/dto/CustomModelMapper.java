package com.backend.ecommerce.dto;

import com.backend.ecommerce.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomModelMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getDefaultPhoneNumber(), user.getAddressList());

    }

    public User reverse(UserDTO userDTO) {
        return new User(userDTO.firstName(), userDTO.lastName(), userDTO.email(), userDTO.defaultPhoneNumber(), userDTO.addressList());
    }
}
