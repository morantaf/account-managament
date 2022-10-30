package com.moranta.accountmanagement.dto;

import java.math.BigDecimal;
import java.util.List;

public class ClientInfoDTO {

    private String name;
    private String surname;
    private BigDecimal balance;
    private List<TransactionInfoDTO> transactions;

    public ClientInfoDTO(String name, String surname, BigDecimal balance, List<TransactionInfoDTO> transactions) {
        this.name = name;
        this.surname = surname;
        this.balance = balance;
        this.transactions = transactions;
    }

    public ClientInfoDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<TransactionInfoDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionInfoDTO> transactions) {
        this.transactions = transactions;
    }
}
