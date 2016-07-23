package com.tonitealive.app.data.repository

import com.tonitealive.app.data.JsonSerializer
import com.tonitealive.app.data.exception.EmailTakenException
import com.tonitealive.app.data.exception.UsernameTakenException
import com.tonitealive.app.data.net.ToniteAliveApi
import com.tonitealive.app.domain.ErrorCodes
import com.tonitealive.app.domain.model.ApiError
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


class DefaultUsersRepositoryTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mockApi: ToniteAliveApi

    @Mock
    lateinit var mockJsonSerializer: JsonSerializer

    lateinit var usersRepository: DefaultUsersRepository

    @Before
    fun setup() {
        usersRepository = DefaultUsersRepository(mockApi, mockJsonSerializer)
    }

    @Test
    fun createUser_shouldReturnNewUser() {
        // With
        val username = "foo"
        val email = "foo@bar.com"
        val password = "foobar"
        val newUser = User(username, email)
        val testObservable = observable<User> { subcriber ->
            subcriber.onNext(newUser)
            subcriber.onCompleted()
        }
        val testSubscriber = TestSubscriber<User>()

        // When
        Mockito.`when`(mockApi.createUser(username, email, password)).thenReturn(testObservable)
        usersRepository.create(username, email, password).subscribe(testSubscriber)

        // Then
        testSubscriber.assertNoErrors()
        testSubscriber.assertCompleted()
        testSubscriber.assertValue(newUser)
    }

    @Test
    fun createUser_shouldReturnErrorWhenUserNotFound() {
        // With
        val username = "foo"
        val email = "foo@bar.com"
        val password = "foobar"
        val testObservable = observable<User> { subcriber ->
            val response = Response.error<User>(400, ResponseBody.create(MediaType.parse("application/json"), ""))
            val error = HttpException(response)
            subcriber.onError(error)
        }
        val testSubscriber = TestSubscriber<User>()
        val error = ApiError(timestamp = 1L,
                status = 400,
                error = "",
                exception = "",
                message = "",
                path = "",
                errorCode = ErrorCodes.USERNAME_TAKEN)

        // When
        Mockito.`when`(mockJsonSerializer.fromString("", ApiError::class.java)).thenReturn(error)
        Mockito.`when`(mockApi.createUser(username, email, password)).thenReturn(testObservable)
        usersRepository.create(username, email, password).subscribe(testSubscriber)

        // Then
        testSubscriber.assertError(UsernameTakenException::class.java)
    }

    @Test
    fun createUser_shouldReturnErrorWhenEmailTaken() {
        // With
        val username = "foo"
        val email = "foo@bar.com"
        val password = "foobar"
        val testObservable = observable<User> { subcriber ->
            val response = Response.error<User>(400, ResponseBody.create(MediaType.parse("application/json"), ""))
            val error = HttpException(response)
            subcriber.onError(error)
        }
        val testSubscriber = TestSubscriber<User>()
        val error = ApiError(timestamp = 1L,
                status = 400,
                error = "",
                exception = "",
                message = "",
                path = "",
                errorCode = ErrorCodes.EMAIL_TAKEN)

        // When
        Mockito.`when`(mockJsonSerializer.fromString("", ApiError::class.java)).thenReturn(error)
        Mockito.`when`(mockApi.createUser(username, email, password)).thenReturn(testObservable)
        usersRepository.create(username, email, password).subscribe(testSubscriber)

        // Then
        testSubscriber.assertError(EmailTakenException::class.java)
    }
}