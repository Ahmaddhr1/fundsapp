package com.ahmad.fundsapp.dto;

public class DepositRequest {
    private Double amount;

    public DepositRequest() {}

    public DepositRequest(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() { return amount; }
}
