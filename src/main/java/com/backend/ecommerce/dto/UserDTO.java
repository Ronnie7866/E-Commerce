package com.backend.ecommerce.dto;


import com.backend.ecommerce.Entity.Cart;
import com.backend.ecommerce.Entity.PhoneNumber;
import com.backend.ecommerce.Entity.Transaction;
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
    private List<PhoneNumber> phoneNumbers;
    private Cart cart;
//    private List<Transaction> transaction;

}
