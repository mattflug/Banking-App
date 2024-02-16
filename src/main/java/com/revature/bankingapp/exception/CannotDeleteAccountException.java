package com.revature.bankingapp.exception;

public class CannotDeleteAccountException extends RuntimeException{
    public CannotDeleteAccountException(String message) {
        super(message);
    }
}
