package com.backend.ecommerce.dto;


import com.backend.ecommerce.entity.Address;
import com.backend.ecommerce.entity.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long defaultPhoneNumber;
    private List<Address> addressList;
    private Cart cart;
//    private List<Transaction> transaction;

}
