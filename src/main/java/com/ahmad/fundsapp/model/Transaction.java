package com.ahmad.fundsapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_account_id")
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "reciever_account_id")
    private Account receiverAccount;

    private Double amount;
    private String type;
    private LocalDateTime createdAt;
    private String receiverUsername;

    public Transaction() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public Account getSenderAccount() { return senderAccount; }
    public void setSenderAccount(Account senderAccount) { this.senderAccount = senderAccount; }

    public Account getReceiverAccount() { return receiverAccount; }
    public void setReceiverAccount(Account receiverAccount) { this.receiverAccount = receiverAccount; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime dateTime) { this.createdAt = dateTime; }

    public String getReceiverUsername() { return receiverUsername; }
    public void setReceiverUsername(String receiverUsername) { this.receiverUsername = receiverUsername; }


}
