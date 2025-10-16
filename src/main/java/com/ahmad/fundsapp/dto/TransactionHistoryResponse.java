package com.ahmad.fundsapp.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class TransactionHistoryResponse {
    private String type;
    private Double amount;
    private String senderUsername;
    private String receiverUsername;
    private LocalDateTime createdAt;

    public TransactionHistoryResponse(String type, Double amount, String senderUsername, String receiverUsername, LocalDateTime createdAt) {
        this.type = type;
        this.amount = amount;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.createdAt = createdAt;
    }

    // Getters
    public String getType() { return type; }
    public Double getAmount() { return amount; }
    public String getSenderUsername() { return senderUsername; }
    public String getReceiverUsername() { return receiverUsername; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
