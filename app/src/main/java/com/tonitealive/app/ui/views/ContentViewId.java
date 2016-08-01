package com.tonitealive.app.ui.views;


public enum ContentViewId {

    USER_PROFILE(0);

    private final int id;

    ContentViewId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}