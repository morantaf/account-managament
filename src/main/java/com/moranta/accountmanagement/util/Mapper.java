package com.moranta.accountmanagement.util;

import com.moranta.accountmanagement.dto.AccountIdDTO;
import com.moranta.accountmanagement.dto.TransactionInfoDTO;
import com.moranta.accountmanagement.model.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class Mapper {

    public TransactionInfoDTO transactionToDTO(Transaction transaction) {
        Long id = transaction.getId();
        BigDecimal amount = transaction.getAmount();
        LocalDateTime dateTime = transaction.getDate();
        Long accountId = transaction.getReceiver().getId();
        AccountIdDTO accountIdDTO = new AccountIdDTO(accountId);
        return new TransactionInfoDTO(id, amount, dateTime,accountIdDTO);
    }
}
