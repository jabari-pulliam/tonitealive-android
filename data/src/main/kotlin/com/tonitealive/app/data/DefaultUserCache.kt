package com.tonitealive.app.data

import android.content.SharedPreferences
import com.tonitealive.app.data.exception.UserNotFoundException
import com.tonitealive.app.domain.model.User
import rx.Observable
import rx.lang.kotlin.observable
import javax.inject.Singleton

@Singleton
class DefaultUserCache(private val serializer: JsonSerializer,
                       private val sharedPreferences: SharedPreferences) : UserCache {



    companion object {
        const val PREF_CACHED_USER = "cachedUser"
    }

    override fun get(username: String): Observable<User> {
        return observable { subscriber ->
            val s = sharedPreferences.getString(username, null)
            if (s != null) {
                if (!subscriber.isUnsubscribed) {
                    val user = serializer.fromString(s, User::class.java)
                    subscriber.onNext(user)
                    subscriber.onCompleted()
                }
                else {
                    subscriber.onError(UserNotFoundException("Could not find user: $username"))
                }
            }
        }
    }

    override fun put(user: User) {
        val s = serializer.toString(user)
        sharedPreferences.edit().putString(PREF_CACHED_USER, s).commit()
    }

    override fun isCached(username: String): Boolean {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isExpired(): Boolean {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun evictAll() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}