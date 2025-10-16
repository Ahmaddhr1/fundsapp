package com.ahmad.fundsapp.dto;

public class TransferResponse {
    private String message;
    private String receiverUsername;

    public TransferResponse(String message, String receiverUsername) {
        this.message = message;
        this.receiverUsername = receiverUsername;
    }

    public String getMessage() { return message; }
    public String getReceiverUsername() { return receiverUsername; }
}
