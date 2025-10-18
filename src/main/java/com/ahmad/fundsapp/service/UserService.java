package com.ahmad.fundsapp.service;

import com.ahmad.fundsapp.dto.UserInfoResponse;
import com.ahmad.fundsapp.model.Account;
import com.ahmad.fundsapp.model.User;
import com.ahmad.fundsapp.repository.AccountRepository;
import com.ahmad.fundsapp.repository.UserRepository;
import com.ahmad.fundsapp.security.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final Jwt jwt;

    @Autowired
    public UserService(UserRepository userRepository, AccountRepository accountRepository, Jwt jwt) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.jwt = jwt;
    }

    public UserInfoResponse getUserInfo (String token) {
        String username = jwt.extractUsername(token);

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        Optional<Account> accountOptional = accountRepository.findByUser_Username(username);
        if (accountOptional.isEmpty()) {
            throw new RuntimeException("Account not found for user");
        }

        Account account = accountOptional.get();

        return new UserInfoResponse(
                user.getUsername(),
                account.getBalance(),
                account.getAccountNumber()
        );
    }
}