package com.tonitealive.app.domain.repositories

import com.tonitealive.app.domain.model.User
import com.tonitealive.app.domain.model.UserProfile
import rx.Observable

interface UsersRepository {

    fun getByUsername(username: String): Observable<User>

    fun getByEmail(email: String): Observable<User>

    fun create(username: String, email: String, password: String): Observable<User>

    fun remove(username: String): Observable<Void>

    fun getProfile(username: String): Observable<UserProfile>

}