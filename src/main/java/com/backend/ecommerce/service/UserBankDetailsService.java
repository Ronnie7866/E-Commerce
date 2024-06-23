package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.UserBankDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserBankDetailsService {

    List<UserBankDetails> getAllBankDetails();

    Optional<UserBankDetails> getBankDetailsById(int id);

    List<UserBankDetails> getBankDetailsByUserId(Long userId);

    List<UserBankDetails> getPrimaryBankDetails(boolean isPrimary);

    List<UserBankDetails> getBankDetailsByStatus(String status);

    UserBankDetails saveBankDetails(UserBankDetails bankDetails);

    void deleteBankDetails(int id);
}
