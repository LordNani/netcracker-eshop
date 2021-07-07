package com.eshop.backend.exceptions;

public class NewPasswordSameAsOldException extends WebException{
    public NewPasswordSameAsOldException() {
        super("NewPasswordSameAsOldException");
    }
}
