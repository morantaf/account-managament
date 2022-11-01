package com.moranta.accountmanagement.service;

import com.moranta.accountmanagement.util.AccountRequest;
import com.moranta.accountmanagement.exception.InvalidInputException;
import com.moranta.accountmanagement.model.Account;
import com.moranta.accountmanagement.model.Client;
import com.moranta.accountmanagement.repository.AccountRepository;
import com.moranta.accountmanagement.repository.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionService transactionService;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName(value = "createNewAccount throws an exception when customerId incorrect")
    void createNewAccountWithIncorrectCustomerId() {
        // given
        String customerId = "ASE123";
        Optional<Client> notFoundClient = Optional.empty();
        AccountRequest request = new AccountRequest(customerId,new BigDecimal(100));
        when(clientRepository.findByCustomerId(customerId)).thenReturn(notFoundClient);

        // when
        Exception exception = assertThrows(InvalidInputException.class,() -> {
            accountService.createNewAccount(request);
        });

        // then
        assertEquals(exception.getMessage(),"Incorrect customerID");
    }

    @Test
    @DisplayName(value = "createNewAccount throws an exception when intialCredit is negative")
    void createNewAccountWithNegativeCredit() {
        //given
        String customerId = "ASE123";
        Client client = new Client("Patrick", customerId,"Dubosc", "patrick@gmail.com");
        Optional<Client> clientOptional = Optional.of(client);
        when(clientRepository.findByCustomerId(customerId)).thenReturn(clientOptional);

        AccountRequest request = new AccountRequest(customerId,new BigDecimal(-100));

        // when
        Exception exception = assertThrows(InvalidInputException.class,() -> {
            accountService.createNewAccount(request);
        });

        // then
        assertEquals(exception.getMessage(),"Initial credit can't be negative");
    }

    @Test
    @DisplayName(value = "createNewAccount with initialCredit = 0 doesn't create a transaction")
    void createNewAccountWithNoCredit() {
        //given
        String customerId = "ASE123";
        BigDecimal amount = new BigDecimal(0);
        Client client = new Client("Patrick", customerId,"Dubosc", "patrick@gmail.com");
        Optional<Client> clientOptional = Optional.of(client);
        when(clientRepository.findByCustomerId(customerId)).thenReturn(clientOptional);

        AccountRequest request = new AccountRequest(customerId,amount);

        // when
        accountService.createNewAccount(request);

        // then
        verify(transactionService, times(0)).createNewTransaction(any(BigDecimal.class), any(LocalDateTime.class), any(Account.class));
    }

    @Test
    @DisplayName(value = "createNewAccount with initialCredit create a transaction")
    void createNewAccountWithPositiveCredit() {
        //given
        String customerId = "ASE123";
        BigDecimal amount = new BigDecimal(50);
        Client client = new Client("Patrick", customerId,"Dubosc", "patrick@gmail.com");
        Optional<Client> clientOptional = Optional.of(client);
        when(clientRepository.findByCustomerId(customerId)).thenReturn(clientOptional);

        AccountRequest request = new AccountRequest(customerId,amount);

        // when
        accountService.createNewAccount(request);

        // then
        verify(transactionService, times(1)).createNewTransaction(any(BigDecimal.class), any(LocalDateTime.class), any(Account.class));
    }

    @Test
    @DisplayName(value = "Save new account properly save an account")
    void saveNewAccount() {
        //given
        Client client = new Client();
        LocalDateTime localDateTime = LocalDateTime.now();

        // when
        accountService.saveNewAccount(new BigDecimal("123"),client,localDateTime);

        // then
        verify(accountRepository, times(1)).save(any(Account.class));
    }
}
