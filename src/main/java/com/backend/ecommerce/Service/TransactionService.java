package com.backend.ecommerce.Service;

import com.backend.ecommerce.Entity.Transaction;
import com.backend.ecommerce.Repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        transaction.setTransactionDate(LocalDateTime.now().toLocalDate());
        transaction.setTransactionTime(LocalDateTime.now().toLocalTime());
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public Transaction updateTransaction(Transaction transaction, Long id) {
        Optional<Transaction> byId = transactionRepository.findById(id);
        if (byId.isPresent()) {
            Transaction updatedTransaction = transactionRepository.save(transaction);
            updatedTransaction.setTransaction_id(transaction.getTransaction_id());
            updatedTransaction.setTransactionAmount(transaction.getTransactionAmount());
            updatedTransaction.setTransactionDate(transaction.getTransactionDate());
            updatedTransaction.setTransactionType(transaction.getTransactionType());
            updatedTransaction.setTransactionStatus(transaction.getTransactionStatus());
            updatedTransaction.setTransactionTime(transaction.getTransactionTime());
            return transactionRepository.save(updatedTransaction);
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }
}
