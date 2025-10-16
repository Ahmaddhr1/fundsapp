package com.ahmad.fundsapp.service;


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
        this.transactionRepository =transactionRepository;
    }

    public Transaction transfer(String token, String recieverAccountNumber , Double amount){
        try{
            String username = jwt.extractUsername(token);
            if(!jwt.validateToken(token ,username)){
                throw new IllegalArgumentException("Invalid or expired token");
            }
            Optional<User> senderUser = userRepository.findByUsername(username);
            if(!senderUser.isPresent()){
                throw new IllegalArgumentException("Username not found");
            }
            Optional<Account> senderAccount = accountRepository.findByUser_Username(username);
            if(!senderAccount.isPresent()){
                throw new IllegalArgumentException("Username not found");
            }
            Optional<Account> recieverAccount = accountRepository.findByAccountNumber(recieverAccountNumber);
            if(!recieverAccount.isPresent()){
                throw new IllegalArgumentException("Username not found");
            }
            if(senderAccount.get().getBalance() < amount){
                throw new IllegalArgumentException("Insufficient funds");
            }
            senderAccount.get().setBalance(senderAccount.get().getBalance() - amount);
            recieverAccount.get().setBalance(recieverAccount.get().getBalance() + amount);

            accountRepository.save(recieverAccount.get());
            accountRepository.save(senderAccount.get());

            Transaction transaction = new Transaction();
            transaction.setSenderAccount(senderAccount.get());
            transaction.setReceiverAccount(recieverAccount.get());
            transaction.setAmount(amount);
            transaction.setType("Transfer");
            return transactionRepository.save(transaction);

        }catch (IllegalArgumentException e){
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Transaction deposit(String token, Double amount) {
        try{
            String username = jwt.extractUsername(token);
            if (!jwt.validateToken(token,username)) {
                throw new IllegalArgumentException("Invalid or expired token");
            }
            Optional<User> user = userRepository.findByUsername(username);
            if(!user.isPresent()){
                throw new IllegalArgumentException("Username not found");
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
            return transactionRepository.save(transaction);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Transaction> getTransactionHistory(String token) {
        try{
            String username = jwt.extractUsername(token);
            if (!jwt.validateToken(token,username)) {
                throw new IllegalArgumentException("Invalid or expired token");
            }
            Optional<Account> account = accountRepository.findByUser_Username(username);
            if(account.isPresent()){
                throw new IllegalArgumentException("Account not found");
            }
            Account account1 = account.get();
            return transactionRepository.findBySenderAccountOrReceiverAccount(account1,account1);
        }catch (IllegalArgumentException e ){
            throw e;
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}


