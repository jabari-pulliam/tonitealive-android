package com.tonitealive.app.domain.service

import com.tonitealive.app.domain.model.AuthToken
import rx.Observable

/**
 * Handles user login/logout
 */
interface AuthService {

    /**
     * Signs the user in using the provided credentials
     *
     * @param username The username
     * @param password The password
     *
     * @return An {@link rx.Observable} that will emit an {@link AuthToken} upon success
     */
    fun login(username: String, password: String): Observable<AuthToken>

    /**
     * Signs the user out of the app
     *
     * @return An {@link rx.Observable} that will emit a null value upon success
     */
    fun logout(): Observable<Void>


}