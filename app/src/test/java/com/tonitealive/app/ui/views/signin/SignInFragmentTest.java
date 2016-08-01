package com.tonitealive.app.ui.views.signin;

import android.app.Application;

import com.tonitealive.app.BuildConfig;
import com.tonitealive.app.data.TokenStore;
import com.tonitealive.app.data.net.ToniteAliveApi;
import com.tonitealive.app.data.service.StormpathAuthService;
import com.tonitealive.app.domain.interactors.SignInUseCase;
import com.tonitealive.app.domain.service.AuthService;
import com.tonitealive.app.internal.di.components.ApplicationComponent;
import com.tonitealive.app.internal.di.components.DaggerApplicationComponent;
import com.tonitealive.app.internal.di.components.DaggerSignInComponent;
import com.tonitealive.app.internal.di.components.SignInComponent;
import com.tonitealive.app.internal.di.modules.ApplicationModule;
import com.tonitealive.app.internal.di.modules.SignInModule;
import com.tonitealive.app.ui.Navigator;
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
@Config(constants = BuildConfig.class, sdk = {SDK_VERSION})
public class SignInFragmentTest {

    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    SignInFragment fragment;

    @Mock SignInPresenter mockPresenter;
    @Mock ToniteAliveApi mockApi;
    @Mock AuthService mockAuthService;
    @Mock TokenStore mockTokenStore;

    public SignInFragmentTest() {}

    @Before
    public void setup() {
        ApplicationComponent appComponent = DaggerApplicationComponent.builder()
            .applicationModule(new TestApplicationModule(RuntimeEnvironment.application))
            .build();
        fragment = SignInFragment.newInstance();
        SignInComponent component = DaggerSignInComponent.builder()
                .applicationComponent(appComponent)
                .signInModule(new TestSignInModule(fragment))
                .build();
        fragment.setComponent(component);
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

    private class TestApplicationModule extends ApplicationModule {

        public TestApplicationModule(Application application) {
            super(application);
        }

        @Override
        public AuthService provideAuthService(StormpathAuthService authService) {
            return mockAuthService;
        }

        @Override
        public ToniteAliveApi provideToniteAliveApi(TokenStore tokenStore) {
            return mockApi;
        }

        @Override
        public TokenStore provideTokenStore() {
            return mockTokenStore;
        }
    }

    private class TestSignInModule extends SignInModule {

        public TestSignInModule(SignInView view) {
            super(view);
        }

        @Override
        public SignInPresenter providePresenter(SignInUseCase signInUseCase, Navigator navigator) {
            return mockPresenter;
        }
    }

}