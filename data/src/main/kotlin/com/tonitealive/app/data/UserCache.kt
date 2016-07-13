package com.tonitealive.app.data

import com.tonitealive.app.domain.model.User
import rx.Observable

/**
 * A user cache
 */
interface UserCache {

    /**
     * Gets an {@link rx.Observable} that will emit a {@link User}
     *
     * @param username The username of the user to retrieve
     */
    fun get(username: String): Observable<User>

    /**
     * Puts an elements into the cache
     *
     * @param user The user to cache
     */
    fun put(user: User)

    /**
     * Checks if the user for the user ID is in the cache.
     *
     * @param username The username of the user
     *
     */
    fun isCached(username: String): Boolean

    /**
     * Checks if the cache is expired
     *
     * @return True if the cache is expired, false otherwise
     */
    fun isExpired(): Boolean

    /**
     * Evicts all items from the cache
     */
    fun evictAll()

}