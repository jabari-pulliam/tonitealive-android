package com.tonitealive.app.data;

import com.google.common.base.Optional;
import com.stormpath.sdk.Stormpath;

import javax.inject.Singleton;

@Singleton
public class StormpathTokenStore implements TokenStore {

    @Override
    public Optional<String> getAuthToken() {
        return Optional.fromNullable(Stormpath.accessToken());
    }
}