package com.ahmad.fundsapp.dto;

public class DepositResponse {
    private String message;
    private Double amount;
    private String error;

    public DepositResponse(String message, Double amount ) {
        this.message = message;
        this.amount = amount;
    }

    public DepositResponse(String error) {
        this.error = error;
    }

    public String getMessage() { return message; }
    public Double getAmount() { return amount; }
    public String getError() { return error; }
}
