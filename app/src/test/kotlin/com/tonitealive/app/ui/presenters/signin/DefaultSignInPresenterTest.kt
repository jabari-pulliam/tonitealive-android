package com.tonitealive.app.ui.presenters.signin

import com.tonitealive.app.data.TokenStore
import com.tonitealive.app.domain.executor.PostExecutionThread
import com.tonitealive.app.domain.executor.ThreadExecutor
import com.tonitealive.app.domain.interactors.SignInUseCase
import com.tonitealive.app.domain.service.AuthService
import com.tonitealive.app.ui.Navigator
import com.tonitealive.app.ui.views.signin.SignInView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import rx.lang.kotlin.observable


class DefaultSignInPresenterTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock lateinit var mockView: SignInView
    @Mock lateinit var mockTokenStore: TokenStore
    @Mock lateinit var mockNavigator: Navigator
    @Mock lateinit var mockExecutor: ThreadExecutor
    @Mock lateinit var mockPostExecutionThread: PostExecutionThread
    @Mock lateinit var mockAuthService: AuthService

    lateinit var signInUseCase: SignInUseCase
    lateinit var presenter: DefaultSignInPresenter

    @Before
    fun setup() {
        signInUseCase = SignInUseCase(mockExecutor, mockPostExecutionThread, mockAuthService)
        presenter = DefaultSignInPresenter(mockView, signInUseCase, mockNavigator)
    }

    @Test
    fun onSignInButtonClicked_showProgressBar() {
        // With
        val username = "username"
        val password = "password"

        // When
        Mockito.`when`(mockView.username).thenReturn(username)
        Mockito.`when`(mockView.password).thenReturn(password)
        Mockito.`when`(mockAuthService.login(username, password)).thenReturn(observable { subscriber ->
            subscriber.onNext(null)
            subscriber.onCompleted()
        })
        presenter.onSignInButtonClicked()

        // Then
        Mockito.verify(mockView).showProgressBar()
    }

    @Test
    fun onSignUpButtonClicked_navigateToSignUpView() {
        // When
        presenter.onSignUpButtonClicked()

        // Then
        Mockito.verify(mockNavigator).goToSignUpView()
    }

}