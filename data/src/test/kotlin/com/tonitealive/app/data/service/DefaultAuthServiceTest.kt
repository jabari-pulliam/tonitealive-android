package com.tonitealive.app.data.service

import com.tonitealive.app.data.TokenStore
import com.tonitealive.app.data.exception.InvalidCredentialsException
import com.tonitealive.app.data.exception.NetworkConnectionException
import com.tonitealive.app.data.net.ToniteAliveApi
import com.tonitealive.app.domain.HttpStatusCodes
import com.tonitealive.app.domain.model.AuthToken
import com.tonitealive.app.domain.model.User
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import retrofit2.Response
import retrofit2.adapter.rxjava.HttpException
import rx.lang.kotlin.observable
import rx.observers.TestSubscriber
import java.io.IOException


class DefaultAuthServiceTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock lateinit var mockApi: ToniteAliveApi
    @Mock lateinit var mockTokenStore: TokenStore

    lateinit var authService: DefaultAuthService

    @Before
    fun setup() {
        authService = DefaultAuthService(mockApi, mockTokenStore)
    }

    @Test
    fun login_shouldStoreAuthToken() {
        // With
        val username = "foo"
        val password = "bar"
        val token = AuthToken(expiresIn = 1234L,
                tokenType = "password",
                accessToken = "mytoken",
                refreshToken = "myothertoken")
        val apiObservable = observable<AuthToken> { subscriber ->
            subscriber.onNext(token)
            subscriber.onCompleted()
        }
        val testSubscriber = TestSubscriber<AuthToken>()

        // When
        Mockito.`when`(mockApi.login(username = username, password = password)).thenReturn(apiObservable)
        authService.login(username, password).subscribe(testSubscriber)

        // Then
        Mockito.verify(mockTokenStore).authToken = token
        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(token)
    }

    @Test
    fun login_connectionFailureShouldReturnError() {
        // With
        val username = "foo"
        val password = "bar"
        val apiObservable = observable<AuthToken> { subscriber ->
            subscriber.onError(IOException())
        }
        val testSubscriber = TestSubscriber<AuthToken>()

        // When
        Mockito.`when`(mockApi.login(username = username, password = password)).thenReturn(apiObservable)
        authService.login(username, password).subscribe(testSubscriber)

        // Then
        testSubscriber.assertError(NetworkConnectionException::class.java)
    }

    @Test
    fun login_invalidCredentialsShouldReturnError() {
        // With
        val username = "foo"
        val password = "bar"
        val apiObservable = observable<AuthToken> { subscriber ->
            val response = Response.error<User>(HttpStatusCodes.STATUS_401_UNAUTHORIZED,
                    ResponseBody.create(MediaType.parse("application/json"), ""))
            val error = HttpException(response)
            subscriber.onError(error)
        }
        val testSubscriber = TestSubscriber<AuthToken>()

        // When
        Mockito.`when`(mockApi.login(username = username, password = password)).thenReturn(apiObservable)
        authService.login(username, password).subscribe(testSubscriber)

        // Then
        testSubscriber.assertError(InvalidCredentialsException::class.java)
    }

    @Test
    fun logout_shouldRemoveTokenAndCallApi() {
        // With
        val apiObservable = observable<Void> { subscriber ->
            subscriber.onNext(null)
            subscriber.onCompleted()
        }
        val testSubscriber = TestSubscriber<Void>()

        // When
        Mockito.`when`(mockApi.logout()).thenReturn(apiObservable)
        authService.logout().subscribe(testSubscriber)

        // Then
        testSubscriber.assertNoErrors()
        testSubscriber.assertCompleted()
        Mockito.verify(mockTokenStore).authToken = null
    }

    @Test
    fun logout_shouldReturnErrorIfConnectionFails() {
        // With
        val apiObservable = observable<Void> { subscriber ->
            subscriber.onError(IOException())
        }
        val testSubscriber = TestSubscriber<Void>()

        // When
        Mockito.`when`(mockApi.logout()).thenReturn(apiObservable)
        authService.logout().subscribe(testSubscriber)

        // Then
        testSubscriber.assertError(NetworkConnectionException::class.java)
    }
}