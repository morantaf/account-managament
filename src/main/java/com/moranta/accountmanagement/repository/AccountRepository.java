package com.moranta.accountmanagement.repository;

import com.moranta.accountmanagement.model.Account;
import com.moranta.accountmanagement.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findAllByOwner(Client client);
}
