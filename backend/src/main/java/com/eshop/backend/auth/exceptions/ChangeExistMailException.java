package com.eshop.backend.auth.exceptions;

public class ChangeExistMailException extends RuntimeException{
    public ChangeExistMailException() {
        super("<h4>This mailbox belongs to another user</h4>\n" +
                "<p>Please try another mailbox</p>\n");
    }
}
