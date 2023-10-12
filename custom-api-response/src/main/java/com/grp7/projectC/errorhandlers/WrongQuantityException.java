package com.grp7.projectC.errorhandlers;

public class WrongQuantityException extends RuntimeException {

    public WrongQuantityException(String message) {
        super(message);
    }

    public void setMessage(String message) {
        this.setMessage(message);
    }
}