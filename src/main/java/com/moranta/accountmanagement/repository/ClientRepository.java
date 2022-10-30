package com.moranta.accountmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.moranta.accountmanagement.model.Client;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByCustomerId(String customerId);

    @Query("select sum(a.amount) from Account a where a.owner= ?1")
    BigDecimal getTotalBalance(Client client);
}
