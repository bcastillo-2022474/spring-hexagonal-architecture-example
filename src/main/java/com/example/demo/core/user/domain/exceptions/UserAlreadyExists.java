package com.example.demo.core.user.domain.exceptions;

public class UserAlreadyExists extends Exception{
    public UserAlreadyExists() {
        super("User already exists");
    }

    public UserAlreadyExists(String message) {
        super(message);
    }
}
