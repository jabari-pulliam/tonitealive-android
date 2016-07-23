package com.tonitealive.app.data.service

import com.tonitealive.app.data.TokenStore
import com.tonitealive.app.data.exception.InvalidCredentialsException
import com.tonitealive.app.data.exception.NetworkConnectionException
import com.tonitealive.app.data.net.ToniteAliveApi
import com.tonitealive.app.domain.HttpStatusCodes
import com.tonitealive.app.domain.model.AuthToken
import com.tonitealive.app.domain.service.AuthService
import retrofit2.adapter.rxjava.HttpException
import rx.Observable
import rx.lang.kotlin.AsyncSubject
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultAuthService @Inject constructor(private val api: ToniteAliveApi,
                                             private val tokenStore: TokenStore) : AuthService {

    override fun login(username: String, password: String): Observable<AuthToken> {
        val subject = AsyncSubject<AuthToken>()
        api.login(username = username, password = password).subscribe({ token ->
            // Store the token
            tokenStore.authToken = token

            // Emit the value
            subject.onNext(token)
            subject.onCompleted()
        }, { error ->
            when (error) {
                is IOException -> subject.onError(NetworkConnectionException("Could not connect to server", error))
                is HttpException -> {
                    when (error.code()) {
                        HttpStatusCodes.STATUS_401_UNAUTHORIZED -> subject.onError(InvalidCredentialsException())
                        else -> subject.onError(error)
                    }
                }
                else -> subject.onError(error)
            }
        })
        return subject
    }

    override fun logout(): Observable<Void> {
        val subject = AsyncSubject<Void>()
        api.logout().subscribe({ value ->
            // Remove the token
            tokenStore.authToken = null

            subject.onNext(value)
            subject.onCompleted()
        }, { error ->
            when (error) {
                is IOException -> subject.onError(NetworkConnectionException(error))
                else -> subject.onError(error)
            }
        })
        return subject
    }
}