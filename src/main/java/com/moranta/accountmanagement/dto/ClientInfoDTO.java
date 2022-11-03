package com.moranta.accountmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ClientInfoDTO {

    private String name;
    private String surname;
    private BigDecimal balance;
    private List<TransactionInfoDTO> transactions;

}


