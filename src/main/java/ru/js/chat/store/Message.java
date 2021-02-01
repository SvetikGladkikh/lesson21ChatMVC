package ru.js.chat.store;

import java.util.Date;

public class Message {

    private Message(){}

    public Message(Date messageDate, String user, String text) {
        this.date = messageDate;
        this.user = user;
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    private Date date;
    private String user;
    private String text;
}
