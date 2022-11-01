package com.moranta.accountmanagement.util;

import com.moranta.accountmanagement.dto.TransactionInfoDTO;
import com.moranta.accountmanagement.model.Account;
import com.moranta.accountmanagement.model.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @InjectMocks
    private Mapper mapper;

    @Test
    @DisplayName(value = "Mapper transform Transation into TransactionInfoDTO correctly")
    void transactionToDTO() {
        // given
        BigDecimal amount = new BigDecimal("200.00");
        LocalDateTime date = LocalDateTime.now();
        Account account = new Account();
        Transaction transaction = new Transaction(amount,date, account);

        // when
        TransactionInfoDTO transactionInfoDTO = mapper.transactionToDTO(transaction);

        // then
        assertEquals(transactionInfoDTO.getAmount(),transaction.getAmount());
        assertEquals(transactionInfoDTO.getDate(),transaction.getDate());
    }
}
