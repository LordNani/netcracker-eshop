package com.eshop.backend.auth.exceptions;

public class WrongEmailOrPasswordException extends WebException {
    public WrongEmailOrPasswordException() {
        super("WrongEmailOrPasswordException");
    }

}
