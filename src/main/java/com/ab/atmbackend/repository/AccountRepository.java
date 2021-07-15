package com.ab.atmbackend.repository;

import com.ab.atmbackend.model.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT a FROM Account a where (a.accountNumber = ?1)")
    Account findAccountByAccountNumber(String accountNumber);
}
