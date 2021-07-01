package com.eshop.backend.Socket;

public class Message {
    private String text;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public String getName() {
        return text;
    }

    public void setName(String text) {
        this.text = text;
    }
}
