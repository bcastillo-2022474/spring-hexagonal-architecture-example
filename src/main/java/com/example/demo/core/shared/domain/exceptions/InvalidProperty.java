package com.example.demo.core.shared.domain.exceptions;

public class InvalidProperty extends Exception{
    public InvalidProperty() {
        super("Invalid property");
    }

    public InvalidProperty(String message) {
        super(message);
    }
}
