package com.moranta.accountmanagement.repository;

import com.moranta.accountmanagement.model.Account;
import com.moranta.accountmanagement.model.Client;
import com.moranta.accountmanagement.model.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    @DisplayName(value = "Find all transactions for a list of account")
    void findAllByReceiverIn() {
        // given
        LocalDateTime dateTime = LocalDateTime.now();
        Client client = new Client("Georges", "ASE6345","Cranston", "Georges@gmail.com");
        clientRepository.save(client);
        Account account1 = new Account(new BigDecimal("200.00"),client,dateTime);
        Account account2 = new Account(new BigDecimal("400.00"),client,dateTime);
        List<Account> accounts = List.of(account1, account2);
        accountRepository.saveAll(accounts);
        Transaction transaction1 = new Transaction(new BigDecimal("200.00"),dateTime, account1);
        Transaction transaction2 = new Transaction(new BigDecimal("200.00"),dateTime, account2);
        transactionRepository.saveAll(List.of(transaction1,transaction2));

        // when
        List<Transaction> transactions = transactionRepository.findAllByReceiverIn(accounts);

        // then
        assertFalse(transactions.isEmpty());
        assertAll(
                () -> assertTrue(transactions.contains(transaction1)),
                () -> assertTrue(transactions.contains(transaction2))
        );

    }
}
