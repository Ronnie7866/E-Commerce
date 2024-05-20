package com.backend.ecommerce.Service;

import com.backend.ecommerce.Entity.Transaction;
import com.backend.ecommerce.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public Transaction saveTransaction(Transaction transaction) {
        transaction.setTransaction_date(LocalDateTime.now().toLocalDate());
        transaction.setTransaction_time(LocalDateTime.now().toLocalTime());
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
            updatedTransaction.setTransaction_amount(transaction.getTransaction_amount());
            updatedTransaction.setTransaction_date(transaction.getTransaction_date());
            updatedTransaction.setTransaction_type(transaction.getTransaction_type());
            updatedTransaction.setTransaction_status(transaction.getTransaction_status());
            updatedTransaction.setTransaction_time(transaction.getTransaction_time());
            return transactionRepository.save(updatedTransaction);
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }
}
