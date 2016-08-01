package com.tonitealive.app.domain.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class UserProfile {
    public abstract Long profileId();
    public abstract String username();
}