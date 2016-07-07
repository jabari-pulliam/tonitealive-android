package com.tonitealive.app.ui.views.signin

import com.tonitealive.app.BuildConfig
import com.tonitealive.app.SDK_VERSION
import com.tonitealive.app.ui.presenters.signin.SignInPresenter
import org.assertj.android.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(SDK_VERSION))
class SignInFragmentTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    lateinit var fragment: SignInFragment

    @Mock
    lateinit var mockPresenter: SignInPresenter

    @Before
    fun setup() {
        fragment = SignInFragment.newInstance()
        //fragment.component = buildComponent()
        SupportFragmentTestUtil.startFragment(fragment)
        fragment.presenter = mockPresenter
    }

    @After
    fun tearDown() {
        Robolectric.reset()
    }

    @Test
    fun onSignInButtonClicked_PresenterCalled() {
        // When
        fragment.signInButton.performClick()

        // Then
        verify(mockPresenter).onSignInButtonClicked()
    }

    @Test
    fun onSignUpButtonClicked_PresenterCalled() {
        // When
        fragment.signUpButton.performClick()

        // Then
        verify(mockPresenter).onSignUpButtonClicked()
    }

    @Test
    fun onForgotPasswordButtonClicked_PresenterCalled() {
        // When
        fragment.forgotPasswordButton.performClick()

        // Then
        verify(mockPresenter).onForgotPasswordButtonClicked()
    }

    @Test
    fun showProgressBar_progressBarShouldBeVisible() {
        // When
        fragment.showProgressBar()

        // Then
        assertThat(fragment.progressBar).isVisible
        assertThat(fragment.progressBar).isIndeterminate
    }

    @Test
    fun hideProgressBar_progressBarShouldNotBeVisible() {
        // When
        fragment.hideProgressBar()

        // Then
        assertThat(fragment.progressBar).isNotVisible
    }

}