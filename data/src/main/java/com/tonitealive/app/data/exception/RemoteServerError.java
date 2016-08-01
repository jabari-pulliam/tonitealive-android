package com.tonitealive.app.data.exception;


public class RemoteServerError extends Exception {
    public RemoteServerError(String message) {
        super(message);
    }

    public RemoteServerError(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteServerError(Throwable cause) {
        super(cause);
    }
}