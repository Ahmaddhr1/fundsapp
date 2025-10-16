package com.ahmad.fundsapp.dto;

public class TransferRequest {
    private String receiverAccountNumber;
    private Double amount;

    public TransferRequest() {}

    public TransferRequest(String receiverAccountNumber, Double amount) {
        this.receiverAccountNumber = receiverAccountNumber;
        this.amount = amount;
    }

    public String getReceiverAccountNumber() { return receiverAccountNumber; }
    public Double getAmount() { return amount; }
}
