package com.moranta.accountmanagement.service;

import com.moranta.accountmanagement.dto.TransactionInfoDTO;
import com.moranta.accountmanagement.exception.NotFoundException;
import com.moranta.accountmanagement.model.Account;
import com.moranta.accountmanagement.model.Client;
import com.moranta.accountmanagement.model.Transaction;
import com.moranta.accountmanagement.repository.ClientRepository;
import com.moranta.accountmanagement.repository.TransactionRepository;
import com.moranta.accountmanagement.dto.ClientInfoDTO;
import com.moranta.accountmanagement.util.Mapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;
    private final Mapper mapper;

    public ClientService(ClientRepository clientRepository, TransactionRepository transactionRepository, Mapper mapper) {
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
    }

    public ClientInfoDTO renderClientInfo(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isEmpty()) {
            throw new NotFoundException();
        }
        Client client = clientOptional.get();
        BigDecimal total = clientRepository.getTotalBalance(client);
        List<Account> accounts = client.getAccounts();
        List<Transaction> transactions = transactionRepository.findAllByReceiverIn(accounts);
        List<TransactionInfoDTO> transactionInfoDTOS = transactions.stream().map(mapper::transactionToDTO).collect(toList());
        return new ClientInfoDTO(client.getName(), client.getSurname(), total, transactionInfoDTOS);
    }
}
