package com.tonitealive.app.data.exception;


public class EmailTakenException extends Exception {

    public EmailTakenException(String email) {
        super(email);
    }

}
