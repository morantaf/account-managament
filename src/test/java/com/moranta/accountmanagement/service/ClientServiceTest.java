package com.moranta.accountmanagement.service;

import com.moranta.accountmanagement.dto.ClientInfoDTO;
import com.moranta.accountmanagement.exception.NotFoundException;
import com.moranta.accountmanagement.model.Account;
import com.moranta.accountmanagement.model.Client;
import com.moranta.accountmanagement.model.Transaction;
import com.moranta.accountmanagement.repository.ClientRepository;
import com.moranta.accountmanagement.repository.TransactionRepository;
import com.moranta.accountmanagement.util.Mapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService service;

    @Mock
    private Mapper mapper;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    @DisplayName(value = "When a client is not found on renderClientInfo, throws an exception")
    void renderClientInfoWithWrongId() {
        // given
        Long id = 1L;
        Optional<Client> notFoundClient = Optional.empty();
        when(clientRepository.findById(id)).thenReturn(notFoundClient);

        // then
        assertThrows(NotFoundException.class,() -> {
            service.renderClientInfo(id);
        });
    }

    @Test
    @DisplayName(value = "Render info correctly")
    void renderClientInfo() {
        // given
        Long id = 1L;
        Client client = new Client("Patrick", "customerId","Dubosc", "patrick@gmail.com");
        LocalDateTime date = LocalDateTime.now();
        BigDecimal balanceAccount1 = new BigDecimal("200");
        BigDecimal balanceAccount2 = new BigDecimal("300");
        BigDecimal totalBalance = balanceAccount1.add(balanceAccount2);

        Account account1 = new Account(balanceAccount1, client, date);
        Account account2 = new Account(balanceAccount2, client, date);

        List<Account> accounts = List.of(account1, account2);

        client.setAccounts(accounts);

        Transaction transaction1 = new Transaction(balanceAccount1, date, account1);
        Transaction transaction2 = new Transaction(balanceAccount2, date, account2);

        List<Transaction> transactions = List.of(transaction1, transaction2);

        Optional<Client> clientOptional = Optional.of(client);

        when(clientRepository.findById(id)).thenReturn(clientOptional);
        when(clientRepository.getTotalBalance(client)).thenReturn(totalBalance);
        when(transactionRepository.findAllByReceiverIn(accounts)).thenReturn(transactions);

        // when
        ClientInfoDTO clientInfoDTO = service.renderClientInfo(id);

        // then
        assertEquals(clientInfoDTO.getBalance(),totalBalance);
        assertEquals(clientInfoDTO.getName(),client.getName());
        assertEquals(clientInfoDTO.getSurname(),client.getSurname());
        verify(mapper,times(2)).transactionToDTO(any(Transaction.class));


    }
}
