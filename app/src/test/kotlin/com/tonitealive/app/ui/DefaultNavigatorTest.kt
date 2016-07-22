package com.tonitealive.app.ui

import com.tonitealive.app.BuildConfig
import com.tonitealive.app.SDK_VERSION
import com.tonitealive.app.ui.views.signin.SignInActivity
import com.tonitealive.app.ui.views.signup.SignUpActivity
import org.assertj.android.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(SDK_VERSION))
class DefaultNavigatorTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    lateinit var navigator: DefaultNavigator

    @Before
    fun setup() {
        navigator = DefaultNavigator(RuntimeEnvironment.application.applicationContext)


    }

    @After
    fun tearDown() {
        Robolectric.reset()
    }

    @Test
    fun goToSignInView_shouldLaunchSignInActivity() {
        // When
        navigator.goToSignInView()

        // Then
        val intent = ShadowApplication.getInstance().nextStartedActivity
        assertThat(intent).hasComponent(RuntimeEnvironment.application.applicationContext, SignInActivity::class.java)
    }

    @Test
    fun goToSignUpView_shouldLaunchSignUpActivity() {
        // When
        navigator.goToSignUpView()

        // Then
        val intent = ShadowApplication.getInstance().nextStartedActivity
        assertThat(intent).hasComponent(RuntimeEnvironment.application.applicationContext, SignUpActivity::class.java)
    }

}