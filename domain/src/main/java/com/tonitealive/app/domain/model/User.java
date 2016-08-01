package com.tonitealive.app.domain.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class User {
    public abstract String username();
    public abstract String email();

    public static User create(String username, String email) {
        return new AutoValue_User(username, email);
    }
}