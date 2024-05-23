package com.backend.ecommerce.Service;

import com.backend.ecommerce.Entity.PhoneNumber;
import com.backend.ecommerce.Repository.PhoneNumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhoneNumberService {

    private final PhoneNumberRepository phoneNumberRepository;

    public List<PhoneNumber> getAll() {
        return phoneNumberRepository.findAll();
    }

    public PhoneNumber phoneId(Long phoneId) {
        return phoneNumberRepository.findById(phoneId).orElseThrow(() -> new RuntimeException("Phone number not found"));
    }

    public PhoneNumber addPhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumberRepository.save(phoneNumber);
    }

    public void deletePhoneNumber(Long phoneId) {
        phoneNumberRepository.deleteById(phoneId);
    }
}
