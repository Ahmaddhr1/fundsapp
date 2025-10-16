package com.ahmad.fundsapp.service;

import com.ahmad.fundsapp.dto.DepositResponse;
import com.ahmad.fundsapp.dto.TransactionHistoryResponse;
import com.ahmad.fundsapp.dto.TransferResponse;
import com.ahmad.fundsapp.model.Account;
import com.ahmad.fundsapp.model.Transaction;
import com.ahmad.fundsapp.model.User;
import com.ahmad.fundsapp.repository.AccountRepository;
import com.ahmad.fundsapp.repository.TransactionRepo;
import com.ahmad.fundsapp.repository.UserRepository;
import com.ahmad.fundsapp.security.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final Jwt jwt;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepo transactionRepository;

    public TransactionService(Jwt jwt, UserRepository userRepository, AccountRepository accountRepository , TransactionRepo transactionRepository) {
        this.jwt = jwt;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public TransferResponse transfer(String token, String receiverAccountNumber, Double amount){
        try{
            String username = jwt.extractUsername(token);
            if(!jwt.validateToken(token, username)){
                throw new IllegalArgumentException("Invalid or expired token");
            }
            Optional<User> senderUser = userRepository.findByUsername(username);
            if(!senderUser.isPresent()){
                throw new IllegalArgumentException("Sender not found");
            }
            Optional<Account> senderAccount = accountRepository.findByUser_Username(username);
            if(!senderAccount.isPresent()){
                throw new IllegalArgumentException("Sender account not found");
            }

            Optional<Account> receiverAccount = accountRepository.findByAccountNumber(receiverAccountNumber);
            if(!receiverAccount.isPresent()){
                throw new IllegalArgumentException("Receiver account not found");
            }
            if (senderAccount.get().getAccountNumber().equals(receiverAccount.get().getAccountNumber())) {
                throw new IllegalArgumentException("You cannot transfer money to your own account");
            }
            if(senderAccount.get().getBalance() < amount){
                throw new IllegalArgumentException("Insufficient funds");
            }

            senderAccount.get().setBalance(senderAccount.get().getBalance() - amount);
            receiverAccount.get().setBalance(receiverAccount.get().getBalance() + amount);

            accountRepository.save(receiverAccount.get());
            accountRepository.save(senderAccount.get());

            Transaction transaction = new Transaction();
            transaction.setSenderAccount(senderAccount.get());
            transaction.setReceiverAccount(receiverAccount.get());
            transaction.setAmount(amount);
            transaction.setType("TRANSFER");
            transaction.setReceiverUsername(receiverAccount.get().getUser().getUsername());
            transactionRepository.save(transaction);

            return new TransferResponse("Transfer Successful", receiverAccount.get().getUser().getUsername());

        } catch (IllegalArgumentException e){
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DepositResponse deposit(String token, Double amount) {
        try{
            String username = jwt.extractUsername(token);
            if (!jwt.validateToken(token, username)) {
                throw new IllegalArgumentException("Invalid or expired token");
            }
            Optional<User> user = userRepository.findByUsername(username);
            if(!user.isPresent()){
                throw new IllegalArgumentException("User not found");
            }
            Optional<Account> account = accountRepository.findByUser_Username(username);
            if(!account.isPresent()){
                throw new IllegalArgumentException("Account not found");
            }
            account.get().setBalance(account.get().getBalance() + amount);
            accountRepository.save(account.get());

            Transaction transaction = new Transaction();
            transaction.setReceiverAccount(account.get());
            transaction.setAmount(amount);
            transaction.setType("DEPOSIT");
            transactionRepository.save(transaction);
            return new DepositResponse("Deposit successful", amount);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TransactionHistoryResponse> getTransactionHistory(String token) {
        try{
            String username = jwt.extractUsername(token);
            if (!jwt.validateToken(token, username)) {
                throw new IllegalArgumentException("Invalid or expired token");
            }
            Optional<Account> account = accountRepository.findByUser_Username(username);
            if(!account.isPresent()){
                throw new IllegalArgumentException("Account not found");
            }
            Account account1 = account.get();
            List<Transaction> transactions= transactionRepository.findBySenderAccountOrReceiverAccount(account1, account1);
            return transactions.stream()
                    .map(t -> new TransactionHistoryResponse(
                            t.getType(),
                            t.getAmount(),
                            t.getSenderAccount() != null ? t.getSenderAccount().getUser().getUsername() : null,
                            t.getReceiverAccount() != null ? t.getReceiverAccount().getUser().getUsername() : t.getReceiverUsername(),
                            t.getCreatedAt()
                    )).toList();
        } catch (IllegalArgumentException e){
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}