package com.ahmad.fundsapp.repository;

import com.ahmad.fundsapp.model.Account;
import com.ahmad.fundsapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t " +
            "WHERE (t.senderAccount = :account OR t.receiverAccount = :account) " +
            "AND (:types IS NULL OR t.type IN :types) " +
            "ORDER BY t.createdAt DESC")
    List<Transaction> findTransactionsByAccountAndTypes(
            @Param("account") Account account,
            @Param("types") List<String> types
    );
}
