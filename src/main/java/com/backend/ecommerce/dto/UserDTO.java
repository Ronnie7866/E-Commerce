package com.backend.ecommerce.dto;

import com.backend.ecommerce.entity.Address;

import java.util.List;

public record UserDTO(String firstName,
                      String lastName,
                      String email,
                      Long defaultPhoneNumber,
                      List<Address> addressList) {
}
