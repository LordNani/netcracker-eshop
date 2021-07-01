package com.eshop.backend.auth.exceptions;

public class UserAlreadyExistsException extends WebException {
    public UserAlreadyExistsException() {
        super("<h4>Email Already Registered</h4>\n" +
                "<p>Your email is already registered.</p>\n" +
                "<p>If you don't know your password please visit the <a href=\"http://localhost:4200/forgot-password\">forgot password</a> page.</p>\n");
    }

}
