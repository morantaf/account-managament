package com.moranta.accountmanagement.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
public class Account {

    @Id
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    private Client owner;

    private LocalDateTime creationDate;

    public Account(BigDecimal amount, Client owner, LocalDateTime creationDate) {
        this.amount = amount;
        this.owner = owner;
        this.creationDate = creationDate;
    }

    public Account() {

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

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
