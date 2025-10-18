package com.ahmad.fundsapp.controller;

import com.ahmad.fundsapp.dto.*;
import com.ahmad.fundsapp.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }



    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody TransferRequest request
    ) {
        try {
            String token = extractTokenFromHeader(authHeader);

            TransferResponse response = transactionService.transfer(
                    token,
                    request.getReceiverAccountNumber(),
                    request.getAmount()
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new TransferResponse(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new TransferResponse(e.getMessage()));
        }
    }
    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody DepositRequest request
    ) {
        try {
            String token = extractTokenFromHeader(authHeader);

            DepositResponse response = transactionService.deposit(
                    token,
                    request.getAmount()
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new DepositResponse(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new DepositResponse(e.getMessage()));
        }
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistory(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam(value = "type", required = false) String typeFilter
    ) {
        try {
            String token = extractTokenFromHeader(authHeader);
            List<TransactionHistoryResponse> transactions = transactionService.getTransactionHistory(token, typeFilter);
            return ResponseEntity.ok(transactions);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    private String extractTokenFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return authHeader;
    }
}