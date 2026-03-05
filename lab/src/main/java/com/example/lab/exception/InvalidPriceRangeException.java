package com.example.lab.exception;

public class InvalidPriceRangeException extends RuntimeException {
    public InvalidPriceRangeException(String message){
        super(message);
    }
}