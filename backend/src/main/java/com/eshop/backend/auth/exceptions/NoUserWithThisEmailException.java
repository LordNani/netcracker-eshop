package com.eshop.backend.auth.exceptions;

public class NoUserWithThisEmailException extends WebException{
    public NoUserWithThisEmailException() {
        super("NoUserWithThisEmailException");
    }
}
