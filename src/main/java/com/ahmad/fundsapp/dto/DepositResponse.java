package com.ahmad.fundsapp.dto;

public class DepositResponse {
    private String message;
    private Double amount;

    public DepositResponse(String message, Double amount) {
        this.message = message;
        this.amount = amount;
    }

    public String getMessage() { return message; }
    public Double getAmount() { return amount; }
}
