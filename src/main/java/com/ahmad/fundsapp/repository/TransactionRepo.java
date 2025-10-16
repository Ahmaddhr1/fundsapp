package com.ahmad.fundsapp.repository;

import com.ahmad.fundsapp.model.Account;
import com.ahmad.fundsapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySenderAccountOrReceiverAccount(Account sender, Account receiver);
}
