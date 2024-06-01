package com.backend.ecommerce.service;

import com.backend.ecommerce.dto.CustomModelMapper;
import com.backend.ecommerce.dto.UserDTO;
import com.backend.ecommerce.entity.Address;
import com.backend.ecommerce.entity.User;
import com.backend.ecommerce.repository.AddressRepository;
import com.backend.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;
    private final CustomModelMapper customModelMapper;

    public Address save(Long userId, Address address) {
        User user = customModelMapper.reverse(userService.getUserById(userId));
        if (user == null) {
            throw new NoSuchElementException("No user found with this userId");
        }
        user.getAddressList().add(address);
        address.setUser(user);
        Address savedAddress = addressRepository.save(address);
        return addressRepository.save(savedAddress);
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address updateAddressByUserId(Address address, Long userId) {
        // Fetch the user by ID
        User user = customModelMapper.reverse(userService.getUserById(userId));

        // Check if the user exists
        if (user != null) {
            // Fetch the address associated with the user
            Optional<Address> userAddressOptional = addressRepository.findByUserId(userId);

            if (userAddressOptional.isPresent()) {
                Address existingAddress = userAddressOptional.get();

                // Update the address details
                existingAddress.setCity(address.getCity());
                existingAddress.setCountry(address.getCountry());
                existingAddress.setAreaCode(address.getAreaCode());
                existingAddress.setPhoneType(address.getPhoneType());
                existingAddress.setPhoneNumber(address.getPhoneNumber());

                // Save the updated address
                return addressRepository.save(existingAddress);
            } else {
                // If the user does not have an address yet, create a new one
                address.setUser(user);
                return addressRepository.save(address);
            }
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist");
        }
    }

    public Address getAddressByUserId(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Adress not found"));
    }

    public String deleteAddressById(Long id) {
        addressRepository.deleteById(id);
        return "Adress deleted";
    }
}
