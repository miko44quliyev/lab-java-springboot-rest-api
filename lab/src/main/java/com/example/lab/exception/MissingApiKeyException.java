package com.example.lab.exception;

public class MissingApiKeyException extends RuntimeException {
    public MissingApiKeyException(String message){
        super(message);
    }
}