package com.moranta.accountmanagement.service;

import com.moranta.accountmanagement.model.Account;
import com.moranta.accountmanagement.model.Client;
import com.moranta.accountmanagement.model.Transaction;
import com.moranta.accountmanagement.repository.ClientRepository;
import com.moranta.accountmanagement.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    void createNewTransaction() {
        // given
        Account account = new Account();
        LocalDateTime dateTime = LocalDateTime.now();

        // when
        transactionService.createNewTransaction(new BigDecimal("123"),dateTime,account);

        // then
        verify(transactionRepository, times(1)).save(any(Transaction.class));

    }
}
