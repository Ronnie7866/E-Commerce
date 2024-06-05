package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.Transaction;
import com.backend.ecommerce.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TransactionService {

    TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public Transaction updateTransaction(Transaction transaction, Long id) {
        Optional<Transaction> byId = transactionRepository.findById(id);
        if (byId.isPresent()) {
            Transaction updatedTransaction = transactionRepository.save(transaction);
            updatedTransaction.setTransactionAmount(transaction.getTransactionAmount());
            updatedTransaction.setTransactionType(transaction.getTransactionType());
            updatedTransaction.setTransactionStatus(transaction.getTransactionStatus());
            return transactionRepository.save(updatedTransaction);
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }



}
