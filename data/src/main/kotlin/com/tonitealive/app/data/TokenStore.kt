package com.tonitealive.app.data

import com.tonitealive.app.domain.model.AuthToken
import javax.inject.Singleton

/**
 * Provides persistent storage for authorization tokens
 */
@Singleton
interface TokenStore {

    var authToken: AuthToken?

}