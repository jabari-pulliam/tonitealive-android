package com.tonitealive.app.data;


import com.google.common.base.Optional;

import javax.inject.Singleton;

/**
 * Provides persistent storage for authorization tokens
 */
@Singleton
public interface TokenStore {

    Optional<String> getAuthToken();

}