package com.eshop.backend.auth.exceptions;

public class NewPasswordSameAsOldException extends WebException{
    public NewPasswordSameAsOldException() {
        super("NewPasswordSameAsOldException");
    }
}
