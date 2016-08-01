package com.tonitealive.app.ui.views.signin

import android.app.Application
import com.tonitealive.app.BuildConfig
import com.tonitealive.app.SDK_VERSION
import com.tonitealive.app.data.TokenStore
import com.tonitealive.app.data.net.ToniteAliveApi
import com.tonitealive.app.data.service.StormpathAuthService
import com.tonitealive.app.domain.interactors.SignInUseCase
import com.tonitealive.app.domain.service.AuthService
import com.tonitealive.app.internal.di.components.DaggerApplicationComponent
import com.tonitealive.app.internal.di.components.DaggerSignInComponent
import com.tonitealive.app.internal.di.modules.ApplicationModule
import com.tonitealive.app.internal.di.modules.SignInModule
import com.tonitealive.app.ui.Navigator
import com.tonitealive.app.ui.presenters.signin.SignInPresenter
import org.assertj.android.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThat
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
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(SDK_VERSION))
class SignInFragmentTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    lateinit var fragment: SignInFragment

    @Mock lateinit var mockPresenter: SignInPresenter
    @Mock lateinit var mockApi: ToniteAliveApi
    @Mock lateinit var mockAuthService: AuthService
    @Mock lateinit var mockTokenStore: TokenStore

    @Before
    fun setup() {
        val appComponent = DaggerApplicationComponent.builder()
            .applicationModule(TestApplicationModule(RuntimeEnvironment.application))
            .build()
        fragment = SignInFragment.newInstance()
        val component = DaggerSignInComponent.builder()
                .applicationComponent(appComponent)
                .signInModule(TestSignInModule(fragment))
                .build()
        fragment.component = component
        SupportFragmentTestUtil.startFragment(fragment)
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

    @Test
    fun showMessage_shouldShowToast() {
        // With
        val message = "hello"

        // When
        fragment.showMessage(message)

        // Then
        assertThat(ShadowToast.showedToast(message)).isTrue()
    }

    inner class TestApplicationModule(application: Application) : ApplicationModule(application) {

        override fun provideAuthService(authService: StormpathAuthService): AuthService {
            return mockAuthService
        }

        override fun provideToniteAliveApi(tokenStore: TokenStore): ToniteAliveApi {
            return mockApi
        }
    }

    inner class TestSignInModule(view: SignInView) : SignInModule(view) {
        override fun providePresenter(signInUseCase: SignInUseCase, navigator: Navigator): SignInPresenter {
            return mockPresenter
        }
    }

}