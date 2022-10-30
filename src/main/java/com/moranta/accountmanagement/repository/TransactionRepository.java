package com.moranta.accountmanagement.repository;

import com.moranta.accountmanagement.model.Account;
import com.moranta.accountmanagement.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByReceiverIn(List<Account> accounts);
}
