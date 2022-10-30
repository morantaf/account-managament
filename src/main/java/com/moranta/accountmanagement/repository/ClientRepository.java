package com.moranta.accountmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.moranta.accountmanagement.model.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByCustomerId(String customerId);
}
