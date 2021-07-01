package com.eshop.backend.auth.exceptions;

public class NeedMailConfirmationException extends WebException {
    public NeedMailConfirmationException() {
        super("<h4>Need email confirm</h4>\n" +
                "<p>To use your account you need click confirmation link in your " +
                "mail box to confirm the email address</p>\n");
    }

}
