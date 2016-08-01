package com.tonitealive.app.ui.views.signup;

import android.app.Application;

import com.tonitealive.app.BuildConfig;
import com.tonitealive.app.data.TokenStore;
import com.tonitealive.app.data.net.ToniteAliveApi;
import com.tonitealive.app.data.service.StormpathAuthService;
import com.tonitealive.app.domain.interactors.SignUpUseCase;
import com.tonitealive.app.domain.service.AuthService;
import com.tonitealive.app.internal.di.components.ApplicationComponent;
import com.tonitealive.app.internal.di.components.DaggerApplicationComponent;
import com.tonitealive.app.internal.di.components.DaggerSignUpComponent;
import com.tonitealive.app.internal.di.components.SignUpComponent;
import com.tonitealive.app.internal.di.modules.ApplicationModule;
import com.tonitealive.app.internal.di.modules.SignUpModule;
import com.tonitealive.app.ui.presenters.signup.SignUpPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static com.tonitealive.app.TestConstants.SDK_VERSION;
import static org.assertj.android.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = {SDK_VERSION})
public class SignUpFragmentTest {

    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock ToniteAliveApi mockApi;
    @Mock TokenStore mockTokenStore;
    @Mock AuthService mockAuthService;
    @Mock SignUpPresenter mockPresenter;

    SignUpFragment signUpFragment;

    public SignUpFragmentTest() {}

    @Before
    public void setup() {
        signUpFragment = SignUpFragment.newInstance();
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new TestApplicationModule(RuntimeEnvironment.application))
                .build();
        SignUpComponent component = DaggerSignUpComponent.builder()
            .applicationComponent(applicationComponent)
            .signUpModule(new TestSignUpModule(signUpFragment))
            .build();
        signUpFragment.setComponent(component);

        SupportFragmentTestUtil.startFragment(signUpFragment);
    }

    @After
    public void tearDown() {
        Robolectric.reset();
    }

    @Test
    public void onSignUpButtonClicked_shouldCallPresenter() {
        // When
        signUpFragment.signUpButton.performClick();

        // Then
        Mockito.verify(mockPresenter).onSignUpButtonClicked();
    }

    @Test
    public void onShowProgressBar_shouldMakeProgressBarVisible() {
        // When
        signUpFragment.showProgressDialog();

        // Then
        assertThat(signUpFragment.progressBar).isVisible();
    }

    @Test
    public void onHideProgressBar_shouldMakeProgressBarGone() {
        // When
        signUpFragment.hideProgressDialog();

        // Then
        assertThat(signUpFragment.progressBar).isGone();
    }

    @Test
    public void onPropertyAccess_shouldReturnFieldValue() {
        // With
        String username = "foo";
        String email = "foobar@test.com";
        String password = "password";
        String confirmPassword = "confpassword";

        // When
        signUpFragment.usernameField.setText(username);
        signUpFragment.emailField.setText(email);
        signUpFragment.passwordField.setText(password);
        signUpFragment.confirmPasswordField.setText(confirmPassword);

        // Then
        assertThat(signUpFragment.getUsername()).isEqualTo(username);
        assertThat(signUpFragment.getEmail()).isEqualTo(email);
        assertThat(signUpFragment.getPassword()).isEqualTo(password);
        assertThat(signUpFragment.getConfirmPassword()).isEqualTo(confirmPassword);
    }

    private class TestApplicationModule extends ApplicationModule {

        public TestApplicationModule(Application application) {
            super(application);
        }

        @Override
        public ToniteAliveApi provideToniteAliveApi(TokenStore tokenStore) {
            return mockApi;
        }

        @Override
        public TokenStore provideTokenStore() {
            return mockTokenStore;
        }

        @Override
        public AuthService provideAuthService(StormpathAuthService authService) {
            return mockAuthService;
        }


    }

    private class TestSignUpModule extends SignUpModule {

        TestSignUpModule(SignUpView view) {
            super(view);
        }

        @Override
        public SignUpPresenter provideSignUpPresenter(SignUpUseCase signUpUseCase) {
            return mockPresenter;
        }
    }
}