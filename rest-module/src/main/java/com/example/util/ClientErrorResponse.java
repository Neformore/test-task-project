package com.example.util;

import lombok.Data;

@Data
public class ClientErrorResponse {

    private String message;
    private long timestamp;

    public ClientErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
