package com.tonitealive.app.domain.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class UserProfile {
    public abstract Long profileId();
    public abstract String username();
    public abstract String profilePhotoUrl();

    public static Builder builder() {
        return new AutoValue_UserProfile.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder profileId(Long id);
        public abstract Builder username(String username);
        public abstract Builder profilePhotoUrl(String url);
        public abstract UserProfile build();
    }
}