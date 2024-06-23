package com.backend.ecommerce.repository;

import com.backend.ecommerce.entity.UserBankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBankDetailsRepository extends JpaRepository<UserBankDetails, Integer> {
    List<UserBankDetails> findByUserId(Long userId);
    List<UserBankDetails> findByIsPrimary(boolean isPrimary);
    List<UserBankDetails> findByStatus(String status);
}
