package com.grp7.projectC;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }

    public void setMessage(String message) {
        this.setMessage(message);
    }
}
