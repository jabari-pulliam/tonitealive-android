package com.tonitealive.app.ui.views.signin;

import com.tonitealive.app.BuildConfig;
import com.tonitealive.app.TestApplication;
import com.tonitealive.app.internal.di.ComponentFactorySupport;
import com.tonitealive.app.internal.di.components.ApplicationComponent;
import com.tonitealive.app.internal.di.components.SignInComponent;
import com.tonitealive.app.internal.di.components.support.SignInComponentSupport;
import com.tonitealive.app.ui.presenters.signin.SignInPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static com.tonitealive.app.TestConstants.SDK_VERSION;
import static org.assertj.android.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = {SDK_VERSION}, application = TestApplication.class)
public class SignInFragmentTest {

    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    SignInFragment fragment;

    @Mock SignInPresenter mockPresenter;

    public SignInFragmentTest() {}

    @Before
    public void setup() {
        TestApplication application = (TestApplication) RuntimeEnvironment.application;
        application.setComponentFactory(new TestComponentFactory());
        fragment = SignInFragment.newInstance();
        SupportFragmentTestUtil.startFragment(fragment);
    }

    @After
    public void tearDown() {
        Robolectric.reset();
    }

    @Test
    public void onSignInButtonClicked_PresenterCalled() {
        // When
        fragment.signInButton.performClick();

        // Then
        verify(mockPresenter).onSignInButtonClicked();
    }

    @Test
    public void onSignUpButtonClicked_PresenterCalled() {
        // When
        fragment.signUpButton.performClick();

        // Then
        verify(mockPresenter).onSignUpButtonClicked();
    }

    @Test
    public void onForgotPasswordButtonClicked_PresenterCalled() {
        // When
        fragment.forgotPasswordButton.performClick();

        // Then
        verify(mockPresenter).onForgotPasswordButtonClicked();
    }

    @Test
    public void showProgressBar_progressBarShouldBeVisible() {
        // When
        fragment.showProgressBar();

        // Then
        assertThat(fragment.progressBar).isVisible();
        assertThat(fragment.progressBar).isIndeterminate();
    }

    @Test
    public void hideProgressBar_progressBarShouldNotBeVisible() {
        // When
        fragment.hideProgressBar();

        // Then
        assertThat(fragment.progressBar).isNotVisible();
    }

    @Test
    public void showMessage_shouldShowToast() {
        // With
        String message = "hello";

        // When
        fragment.showMessage(message);

        // Then
        assertThat(ShadowToast.showedToast(message)).isTrue();
    }

    private class TestComponentFactory extends ComponentFactorySupport {
        @Override
        public SignInComponent createSignInComponent(ApplicationComponent applicationComponent, SignInView signInView) {
            return new SignInComponentSupport() {
                @Override
                public void inject(SignInFragment signInFragment) {
                    signInFragment.presenter = mockPresenter;
                }
            };
        }
    }

}