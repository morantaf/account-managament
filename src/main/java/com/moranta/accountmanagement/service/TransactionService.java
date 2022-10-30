package com.moranta.accountmanagement.service;

import com.moranta.accountmanagement.model.Account;
import com.moranta.accountmanagement.model.Transaction;
import com.moranta.accountmanagement.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void createNewTransaction(BigDecimal amount, LocalDateTime date, Account receiver) {
        Transaction transaction = new Transaction(amount,date, receiver);
        transactionRepository.save(transaction);
    }
}
