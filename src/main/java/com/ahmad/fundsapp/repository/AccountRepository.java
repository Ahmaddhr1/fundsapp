package com.ahmad.fundsapp.repository;

import com.ahmad.fundsapp.model.Account;
import com.ahmad.fundsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountNumber(String accountNumber);
    Optional<Account> findByUser_Username(String username);


}
