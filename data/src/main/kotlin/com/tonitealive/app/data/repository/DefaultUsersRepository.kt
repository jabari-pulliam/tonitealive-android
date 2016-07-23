package com.tonitealive.app.data.repository

import com.tonitealive.app.data.JsonSerializer
import com.tonitealive.app.data.exception.*
import com.tonitealive.app.data.net.ToniteAliveApi
import com.tonitealive.app.domain.ErrorCodes
import com.tonitealive.app.domain.model.ApiError
import com.tonitealive.app.domain.model.User
import com.tonitealive.app.domain.model.UserProfile
import com.tonitealive.app.domain.repositories.UsersRepository
import retrofit2.adapter.rxjava.HttpException
import rx.Observable
import rx.lang.kotlin.AsyncSubject
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultUsersRepository @Inject constructor(private val api: ToniteAliveApi,
                                                 private val jsonSerializer: JsonSerializer) : UsersRepository {

    override fun getByUsername(username: String): Observable<User> {
        val subject = AsyncSubject<User>()
        api.getUserByUsername(username).subscribe({user ->
            subject.onNext(user)
            subject.onCompleted()
        }, {error ->
            val cause = error.cause
            when (cause) {
                is IOException ->
                    subject.onError(NetworkConnectionException("Could not connect to server", cause))

                is HttpException ->
                    subject.onError(UserNotFoundException(cause.message(), cause))

                else -> subject.onError(cause)
            }
        })
        return subject
    }

    override fun getByEmail(email: String): Observable<User> {
        val subject = AsyncSubject<User>()
        api.getUserByEmail(email).subscribe({user ->
            subject.onNext(user)
            subject.onCompleted()
        }, {error ->
            val cause = error.cause
            when (cause) {
                is IOException ->
                    subject.onError(NetworkConnectionException("Could not connect to server", cause))
                is HttpException ->
                    subject.onError(UserNotFoundException("Could not find user with email $email", cause))
                else -> subject.onError(cause)
            }
        })
        return subject
    }

    override fun create(username: String, email: String, password: String): Observable<User> {
        val subject = AsyncSubject<User>()
        api.createUser(username, email, password).subscribe({user ->
            subject.onNext(user)
            subject.onCompleted()
        }, {error ->
            when (error) {
                is IOException -> subject.onError(NetworkConnectionException("Could not connect to server", error))
                is HttpException -> {
                    when (error.code()) {
                        400 -> {
                            val errorBody = jsonSerializer.fromString(error.response().errorBody().string(), ApiError::class.java)
                            when (errorBody.errorCode) {
                                ErrorCodes.USERNAME_TAKEN -> subject.onError(UsernameTakenException(username))
                                ErrorCodes.EMAIL_TAKEN -> subject.onError(EmailTakenException(email))
                                else -> subject.onError(ValidationFailedException(errorBody.message))
                            }
                        }
                    }
                }
                else -> subject.onError(error)
            }
        })
        return subject
    }

    override fun remove(username: String): Observable<Void> {
        val subject = AsyncSubject<Void>()
        api.removeUser(username).subscribe({
            subject.onNext(null)
            subject.onCompleted()
        }, {error ->
            val cause = error.cause
            when (cause) {
                is IOException -> subject.onError(NetworkConnectionException("Could not connect to server", cause))
                is HttpException -> subject.onError(UserNotFoundException("Could not find user $username", cause))
                else -> subject.onError(cause)
            }
        })
        return subject
    }

    override fun getProfile(username: String): Observable<UserProfile> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}