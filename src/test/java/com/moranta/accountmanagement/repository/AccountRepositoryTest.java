package com.moranta.accountmanagement.repository;

import com.moranta.accountmanagement.model.Account;
import com.moranta.accountmanagement.model.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName(value= "Find all account for a client")
    void findAllByOwner() {
        // given
        LocalDateTime dateTime = LocalDateTime.now();
        Client client = new Client("Marielle", "ASE6345","Epacka", "marielle@gmail.com");
        clientRepository.save(client);
        Account account1 = new Account(new BigDecimal("200.00"),client,dateTime);
        Account account2 = new Account(new BigDecimal("100.00"),client,dateTime);
        accountRepository.saveAll(List.of(account1, account2));

        // when
        List<Account> accounts = accountRepository.findAllByOwner(client);

        //then
        assertFalse(accounts.isEmpty());
        assertAll(
                () -> assertTrue(accounts.contains(account1)),
                () -> assertTrue(accounts.contains(account2))
        );
    }

    @Test
    @DisplayName(value= "Return an empty list when a user doesn't have any account")
    void findAllByOwnerReturnsEmptyList() {
        // given
        Client client = new Client("Murielle", "ASE6345","Fressini", "murielle@gmail.com");
        clientRepository.save(client);

        // when
        List<Account> accounts = accountRepository.findAllByOwner(client);

        // then
        assertTrue(accounts.isEmpty());
    }

}
