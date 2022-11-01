package com.moranta.accountmanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionInfoDTO {

    private Long id;

    private BigDecimal amount;

    private LocalDateTime date;

    private AccountIdDTO receiver;

    public TransactionInfoDTO(Long id, BigDecimal amount, LocalDateTime date, AccountIdDTO receiver) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.receiver = receiver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public AccountIdDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(AccountIdDTO receiver) {
        this.receiver = receiver;
    }
}
