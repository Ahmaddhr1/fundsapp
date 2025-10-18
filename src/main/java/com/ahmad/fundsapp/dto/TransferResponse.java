package com.ahmad.fundsapp.dto;

public class TransferResponse {
    private String message;
    private String receiverUsername;
    private String error;

    public TransferResponse(String message, String receiverUsername ) {
        this.message = message;
        this.receiverUsername = receiverUsername;
    }
    public TransferResponse(String error ) {
        this.error = error;
    }

    public String getMessage() { return message; }
    public String getReceiverUsername() { return receiverUsername; }
    public String getError() { return error; }
}
