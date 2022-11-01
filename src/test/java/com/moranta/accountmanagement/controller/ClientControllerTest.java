package com.moranta.accountmanagement.controller;

import com.moranta.accountmanagement.model.Account;
import com.moranta.accountmanagement.model.Client;
import com.moranta.accountmanagement.model.Transaction;
import com.moranta.accountmanagement.repository.AccountRepository;
import com.moranta.accountmanagement.repository.ClientRepository;
import com.moranta.accountmanagement.repository.TransactionRepository;
import com.moranta.accountmanagement.service.ClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName(value = "/api/v1/clients/$id - return 400 if id in wrong format")
    void getClientInfoWithWrongId() throws Exception{

        // when
        mockMvc.perform(get("/api/v1/clients/az"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(value = "/api/v1/clients/$id - return good data")
    void getClientInfo() throws Exception{
        // given
        Client client = createTestData();

        // when
        mockMvc.perform(get("/api/v1/clients/{id}",client.getId()))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.name").value(client.getName()),
                        jsonPath("$.surname").value(client.getSurname()),
                        jsonPath("$.balance").value("500.0"),
                        jsonPath("$.transactions").isNotEmpty()
                );
    }

    Client createTestData() {
        Client client = new Client("Random", "QSD123","Name", "random@gmail.com");
        LocalDateTime date = LocalDateTime.now();
        BigDecimal balanceAccount1 = new BigDecimal("200");
        BigDecimal balanceAccount2 = new BigDecimal("300");
        clientRepository.save(client);

        Account account1 = new Account(balanceAccount1, client, date);
        Account account2 = new Account(balanceAccount2, client, date);

        List<Account> accounts = List.of(account1, account2);
        accountRepository.saveAll(accounts);

        client.setAccounts(accounts);

        Transaction transaction1 = new Transaction(balanceAccount1, date, account1);
        Transaction transaction2 = new Transaction(balanceAccount2, date, account2);

        List<Transaction> transactions = List.of(transaction1, transaction2);
        transactionRepository.saveAll(transactions);

        return client;
    }
}
