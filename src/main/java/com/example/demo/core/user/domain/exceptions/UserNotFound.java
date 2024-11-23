package com.example.demo.core.user.domain.exceptions;

public class UserNotFound extends Exception{
    public UserNotFound() {
        super("User not found");
    }

    public UserNotFound(String message) {
        super(message);
    }
}
