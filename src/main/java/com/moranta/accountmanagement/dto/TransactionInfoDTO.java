package com.moranta.accountmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TransactionInfoDTO {

    private Long id;

    private BigDecimal amount;

    private LocalDateTime date;

    private AccountIdDTO receiver;

}
