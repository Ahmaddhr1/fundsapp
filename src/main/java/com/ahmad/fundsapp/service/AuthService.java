package com.ahmad.fundsapp.service;

import com.ahmad.fundsapp.dto.AuthResponse;
import com.ahmad.fundsapp.model.Account;
import com.ahmad.fundsapp.model.User;
import com.ahmad.fundsapp.repository.AccountRepository;
import com.ahmad.fundsapp.repository.UserRepository;
import com.ahmad.fundsapp.security.Jwt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final Jwt jwt;
    private UserRepository userRepository;
    private AccountRepository accountRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(Jwt jwt, UserRepository userRepository, AccountRepository accountRepository) {
        this.jwt = jwt;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public AuthResponse register(String username, String password){

        if(username == null || password == null){
            throw new IllegalArgumentException("All credentials are required");
        }
        if(userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("Username is already in use");
        }
        String hashedPassword = passwordEncoder.encode(password);


        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        User savedUser=userRepository.save(user);

        Account account = new Account();
        account.setAccountNumber(UUID.randomUUID().toString().replace("-", "").substring(0, 10));
        account.setBalance(5000.0);
        account.setUser(user);
        accountRepository.save(account);

        String token = jwt.generateToken(savedUser.getUsername());
        return new AuthResponse(savedUser.getId(), savedUser.getUsername(), token);

    }

    public AuthResponse login(String username, String password) {
        try {
            if (username == null || password == null) {
                throw new IllegalArgumentException("All credentials are required");
            }
            Optional<User> optionalUser = userRepository.findByUsername(username);
            if (optionalUser.isEmpty()) {
                throw new IllegalArgumentException("Invalid username or password");
            }
            User user = optionalUser.get();
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new IllegalArgumentException("Invalid username or password");
            }
            String token = jwt.generateToken(user.getUsername());
            return new AuthResponse(user.getId(),user.getUsername(), token);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }


}
