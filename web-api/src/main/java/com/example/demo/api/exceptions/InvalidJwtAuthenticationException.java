package com.example.demo.api.exceptions;

public class InvalidJwtAuthenticationException extends RuntimeException {
    public InvalidJwtAuthenticationException(String msg) {
        super(msg);
    }
}
