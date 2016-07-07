package com.tonitealive.app.ui.presenters.signin

import com.tonitealive.app.data.TokenStore
import com.tonitealive.app.ui.views.signin.SignInView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


class DefaultSignInPresenterTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mockView: SignInView

    @Mock
    lateinit var mockTokenStore: TokenStore

    lateinit var presenter: DefaultSignInPresenter

    @Before
    fun setup() {
        presenter = DefaultSignInPresenter(mockView)
    }

    @Test
    fun onSignInButtonClicked_showProgressBar() {
        // When
        presenter.onSignInButtonClicked()

        // Then
        Mockito.verify(mockView).showProgressBar()
    }

}