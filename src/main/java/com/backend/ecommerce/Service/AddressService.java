package com.backend.ecommerce.Service;

import com.backend.ecommerce.Entity.Address;
import com.backend.ecommerce.Repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address updateAddressById(Address address, Long id) {
        Optional<Address> byId = addressRepository.findById(id);
        if (byId.isPresent()) {
            Address existingAddress = byId.get();
            existingAddress.setUser(address.getUser());
            existingAddress.setCity(address.getCity());
            existingAddress.setCountry(address.getCountry());
            existingAddress.setAreaCode(address.getAreaCode());
            existingAddress.setPhoneType(address.getPhoneType());
            existingAddress.setPhoneNumber(address.getPhoneNumber());
            return addressRepository.save(existingAddress);
        }
        return null;
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Adress not found"));
    }

    public String deleteAddressById(Long id) {
        addressRepository.deleteById(id);
        return "Adress deleted";
    }
}
