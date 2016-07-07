package com.tonitealive.app.data

import android.content.SharedPreferences
import com.tonitealive.app.domain.model.AuthToken


class DefaultTokenStore(private val sharedPreferences: SharedPreferences,
                        private val serializer: ObjectSerializer) : TokenStore {

    companion object {
        internal val PREF_AUTH_TOKEN = "authToken"
    }

    override var authToken: AuthToken?
        get() {
            val tokenString = sharedPreferences.getString(PREF_AUTH_TOKEN, null)
            if (tokenString != null) {
                val token = serializer.fromString(tokenString, AuthToken::class.java)
                return token
            }
            return null
        }
        set(value) {
            if (value == null)
                sharedPreferences.edit().remove(PREF_AUTH_TOKEN)

            val tokenString = serializer.toString(value)
            sharedPreferences.edit()
                    .putString(PREF_AUTH_TOKEN, tokenString)
                    .apply()
        }
}