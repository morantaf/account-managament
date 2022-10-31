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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName(value= "Find customer with customer Id")
    void findByCustomerId() {
        // given
        String customerId = "ASE6345";
        Client client = new Client("Patrick", customerId,"Dubosc", "patrick@gmail.com");
        clientRepository.save(client);

        // when
        Optional<Client> clientFound = clientRepository.findByCustomerId(customerId);

        // then
        assertTrue(clientFound.isPresent());
        assertEquals(clientFound.get(),client);

    }

    @Test
    @DisplayName(value= "Don't find customer with wrong customer Id")
    void dontFindByCustomerId() {
        // given
        String customerId = "ASE65";

        // when
        Optional<Client> clientFound = clientRepository.findByCustomerId(customerId);

        // then
        assertFalse(clientFound.isPresent());
    }

    @Test
    @DisplayName(value = "Return the correct sum of all the accounts balances to get total balance for client")
    void getTotalBalance() {
        // given
        LocalDateTime dateTime = LocalDateTime.now();
        Client client = new Client("Patrick", "ASE6345","Dubosc", "patrick@gmail.com");
        clientRepository.save(client);
        Account account1 = new Account(new BigDecimal("200.00"),client,dateTime);
        Account account2 = new Account(new BigDecimal("100.00"),client,dateTime);
        Account account3 = new Account(new BigDecimal("400.00"),client,dateTime);
        accountRepository.saveAll(List.of(account1, account2, account3));

        // when
        BigDecimal totalBalance = clientRepository.getTotalBalance(client);

        // then
        assertEquals(totalBalance,new BigDecimal("700.00"));

    }
}
