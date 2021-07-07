package com.eshop.backend.exceptions;

public class NoUserWithThisEmailException extends WebException{
    public NoUserWithThisEmailException() {
        super("NoUserWithThisEmailException");
    }
}
