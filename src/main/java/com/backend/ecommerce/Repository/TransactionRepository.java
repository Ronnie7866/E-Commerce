package com.backend.ecommerce.Repository;

import com.backend.ecommerce.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
