package com.tonitealive.app.domain;

public enum MessageId {

    SIGN_IN_SUCCESS(0);

    private final int code;

    MessageId(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
