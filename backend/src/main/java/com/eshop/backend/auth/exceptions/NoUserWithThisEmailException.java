package com.eshop.backend.auth.exceptions;

public class NoUserWithThisEmailException extends WebException{
    public NoUserWithThisEmailException() {
        super("<h4>Error wrong email</h4>\n" +
                "<p>User with this email does not exist</p>");
    }
}
