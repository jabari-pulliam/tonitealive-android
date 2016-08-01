package com.tonitealive.app.domain.service;

import com.tonitealive.app.domain.model.User;

import rx.Observable;

/**
 * Handles user login, logout, and registration
 */
public interface AuthService {

    /**
     * Signs the user in using the provided credentials
     *
     * @param username The username
     * @param password The password
     *
     * @return An {@link rx.Observable} that will emit a token upon success
     */
    Observable<Void> login(String username, String password);

    /**
     * Signs the user out of the app
     */
    void logout();

    /**
     * Register a new user
     *
     * @param username The username
     * @param email The email address
     * @param firstName The user's first name (optional)
     * @param lastName The user's last name (optional)
     *
     * @return An observable that emits a null value when the operation completes without error
     */
    Observable<Void> register(String username, String email, String password, String firstName, String lastName);

    /**
     * Returns the currently logged in user or null if there is not one
     *
     * @return The user or null
     */
    Observable<User> getLoggedInUser();

}