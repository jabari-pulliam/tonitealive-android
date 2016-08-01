package com.tonitealive.app.ui;

import android.content.Intent;

import com.tonitealive.app.BuildConfig;
import com.tonitealive.app.ui.views.signin.SignInActivity;
import com.tonitealive.app.ui.views.signup.SignUpActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static com.tonitealive.app.TestConstants.SDK_VERSION;
import static org.assertj.android.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = {SDK_VERSION})
public class DefaultNavigatorTest {

    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    private DefaultNavigator navigator;

    public DefaultNavigatorTest() {}

    @Before
    public void setup() {
        navigator = new DefaultNavigator(RuntimeEnvironment.application.getApplicationContext());


    }

    @After
    public void tearDown() {
        Robolectric.reset();
    }

    @Test
    public void goToSignInView_shouldLaunchSignInActivity() {
        // When
        navigator.goToSignInView();

        // Then
        Intent intent = ShadowApplication.getInstance().getNextStartedActivity();
        assertThat(intent).hasComponent(RuntimeEnvironment.application.getApplicationContext(), SignInActivity.class);
    }

    @Test
    public void goToSignUpView_shouldLaunchSignUpActivity() {
        // When
        navigator.goToSignUpView();

        // Then
        Intent intent = ShadowApplication.getInstance().getNextStartedActivity();
        assertThat(intent).hasComponent(RuntimeEnvironment.application.getApplicationContext(), SignUpActivity.class);
    }

}