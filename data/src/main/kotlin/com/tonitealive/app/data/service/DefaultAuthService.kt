package com.tonitealive.app.data.service

import com.tonitealive.app.data.JsonSerializer
import com.tonitealive.app.data.TokenStore
import com.tonitealive.app.data.exception.InvalidCredentialsException
import com.tonitealive.app.data.exception.NetworkConnectionException
import com.tonitealive.app.data.net.ApiService
import com.tonitealive.app.domain.model.AuthToken
import com.tonitealive.app.domain.service.AuthService
import retrofit2.adapter.rxjava.HttpException
import rx.Observable
import rx.lang.kotlin.AsyncSubject
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultAuthService @Inject constructor(private val apiService: ApiService,
                                             private val tokenStore: TokenStore,
                                             private val jsonSerializer: JsonSerializer) : AuthService {

    override fun login(username: String, password: String): Observable<AuthToken> {
        val subject = AsyncSubject<AuthToken>()
        apiService.login(username = username, password = password).subscribe({ token ->
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
                        403 -> subject.onError(InvalidCredentialsException())
                        else -> subject.onError(error)
                    }
                }
                else -> subject.onError(error)
            }
        })
        return subject
    }

    override fun logout(): Observable<Void> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}