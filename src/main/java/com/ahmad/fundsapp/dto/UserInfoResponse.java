package com.ahmad.fundsapp.dto;

public class UserInfoResponse {
    private String username;
    private double balance;
    private String accountNumber;

    public UserInfoResponse(String username,double balance,String accountNumber){
        this.username = username;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
