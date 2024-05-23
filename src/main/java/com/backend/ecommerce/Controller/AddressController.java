package com.backend.ecommerce.Controller;


import com.backend.ecommerce.Entity.Address;
import com.backend.ecommerce.Entity.User;
import com.backend.ecommerce.Repository.AddressRepository;
import com.backend.ecommerce.Repository.UserRepository;
import com.backend.ecommerce.Service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
@AllArgsConstructor
public class AddressController {

    private final UserRepository userRepository;
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address save = addressService.save(address);
        return ResponseEntity.ok().body(save);
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddress() {
        List<Address> addressList = addressService.getAll();
        return ResponseEntity.ok().body(addressList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Address address = addressService.getAddressById(id);
        return ResponseEntity.ok().body(address);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAddress(@RequestBody Address address) {

        addressService.deleteAddressById(address.getId());
        return ResponseEntity.ok("Address is deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable Long id) {
        Address address1 = addressService.updateAddressById(address, id);
        return ResponseEntity.ok().body(address1);
    }
}