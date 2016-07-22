package com.tonitealive.app.ui.views.signup

import android.app.Application
import android.content.SharedPreferences
import com.tonitealive.app.BuildConfig
import com.tonitealive.app.SDK_VERSION
import com.tonitealive.app.ToniteAliveApplication
import com.tonitealive.app.data.JsonSerializer
import com.tonitealive.app.data.TokenStore
import com.tonitealive.app.data.net.ApiService
import com.tonitealive.app.domain.service.AuthService
import com.tonitealive.app.internal.di.components.DaggerSignUpComponent
import com.tonitealive.app.internal.di.modules.ApplicationModule
import com.tonitealive.app.internal.di.modules.SignUpModule
import com.tonitealive.app.ui.presenters.signup.SignUpPresenter
import org.assertj.android.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(SDK_VERSION))
class SignUpFragmentTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock lateinit var mockApiService: ApiService
    @Mock lateinit var mockTokenStore: TokenStore
    @Mock lateinit var mockAuthService: AuthService
    @Mock lateinit var mockPresenter: SignUpPresenter

    lateinit var signUpFragment: SignUpFragment

    @Before
    fun setup() {
        signUpFragment = SignUpFragment.newInstance()
        val applicationComponent = (RuntimeEnvironment.application as ToniteAliveApplication).applicationComponent
        val component = DaggerSignUpComponent.builder()
            .applicationComponent(applicationComponent)
            .signUpModule(TestSignUpModule(signUpFragment))
            .build()
        signUpFragment.component = component

        SupportFragmentTestUtil.startFragment(signUpFragment)
    }

    @After
    fun tearDown() {
        Robolectric.reset()
    }

    @Test
    fun onSignUpButtonClicked_shouldCallPresenter() {
        // When
        signUpFragment.signUpButton.performClick()

        // Then
        Mockito.verify(mockPresenter).onSignUpButtonClicked()
    }

    @Test
    fun onShowProgressBar_shouldMakeProgressBarVisible() {
        // When
        signUpFragment.showProgressDialog()

        // Then
        assertThat(signUpFragment.progressBar).isVisible
    }

    @Test
    fun onHideProgressBar_shouldMakeProgressBarGone() {
        // When
        signUpFragment.hideProgressDialog()

        // Then
        assertThat(signUpFragment.progressBar).isGone
    }

    @Test
    fun onPropertyAccess_shouldReturnFieldValue() {
        // With
        val username = "foo"
        val email = "foobar@test.com"
        val password = "password"
        val confirmPassword = "confpassword"

        // When
        signUpFragment.usernameField.setText(username)
        signUpFragment.emailField.setText(email)
        signUpFragment.passwordField.setText(password)
        signUpFragment.confirmPasswordField.setText(confirmPassword)

        // Then
        assertThat(signUpFragment.username).isEqualTo(username)
        assertThat(signUpFragment.email).isEqualTo(email)
        assertThat(signUpFragment.password).isEqualTo(password)
        assertThat(signUpFragment.confirmPassword).isEqualTo(confirmPassword)
    }


    inner class TestApplicationModule(application: Application) : ApplicationModule(application) {
        override fun provideApiService(): ApiService {
            return mockApiService
        }

        override fun provideTokenStore(sharedPreferences: SharedPreferences, serializer: JsonSerializer): TokenStore {
            return mockTokenStore
        }

        override fun provideAuthService(apiService: ApiService, tokenStore: TokenStore): AuthService {
            return mockAuthService
        }
    }

    inner class TestSignUpModule(view: SignUpView) : SignUpModule(view) {
        override fun provideSignUpPresenter(): SignUpPresenter {
            return mockPresenter
        }
    }
}