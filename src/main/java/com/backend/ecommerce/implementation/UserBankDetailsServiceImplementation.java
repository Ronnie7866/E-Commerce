package com.backend.ecommerce.implementation;

import com.backend.ecommerce.entity.UserBankDetails;
import com.backend.ecommerce.repository.UserBankDetailsRepository;
import com.backend.ecommerce.service.UserBankDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserBankDetailsServiceImplementation implements UserBankDetailsService {

    private final UserBankDetailsRepository repository;

    public List<UserBankDetails> getAllBankDetails() {
        return repository.findAll();
    }

    public Optional<UserBankDetails> getBankDetailsById(int id) {
        return repository.findById(id);
    }

    public List<UserBankDetails> getBankDetailsByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<UserBankDetails> getPrimaryBankDetails(boolean isPrimary) {
        return repository.findByIsPrimary(isPrimary);
    }

    public List<UserBankDetails> getBankDetailsByStatus(String status) {
        return repository.findByStatus(status);
    }

    public UserBankDetails saveBankDetails(UserBankDetails bankDetails) {
        return repository.save(bankDetails);
    }

    public void deleteBankDetails(int id) {
        repository.deleteById(id);
    }
}
