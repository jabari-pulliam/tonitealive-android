package com.tonitealive.app.data.cache;

import com.google.common.base.Optional;
import com.tonitealive.app.domain.model.User;


public interface UserCache {

    /**
     * Puts the current user in the cache
     *
     * @param user The user
     */
    void putCurrentUser(User user);

    /**
     * Retrieves the current user from the cache
     *
     * @return The cached user or null
     */
    Optional<User> getCurrentUser();

    /**
     * Clears the current user from the cache
     */
    void clearCurrentUser();

}
