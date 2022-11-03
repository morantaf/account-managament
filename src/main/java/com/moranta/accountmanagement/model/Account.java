package com.moranta.accountmanagement.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private BigDecimal amount;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Client owner;

    @NonNull
    private LocalDateTime creationDate;

    @OneToMany
    private List<Transaction> transactions;

}
