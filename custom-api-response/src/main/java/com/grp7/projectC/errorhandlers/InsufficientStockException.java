package com.grp7.projectC.errorhandlers;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(String message) {
        super(message);
    }

    public void setMessage(String message) {
        this.setMessage(message);
    }
}
