package com.tonitealive.app.ui.views.signup;

import com.tonitealive.app.BuildConfig;
import com.tonitealive.app.TestApplication;
import com.tonitealive.app.ToniteAliveApplication;
import com.tonitealive.app.internal.di.DefaultComponentFactory;
import com.tonitealive.app.internal.di.components.ApplicationComponent;
import com.tonitealive.app.internal.di.components.SignUpComponent;
import com.tonitealive.app.internal.di.components.support.ApplicationComponentSupport;
import com.tonitealive.app.internal.di.components.support.SignUpComponentSupport;
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
@Config(constants = BuildConfig.class, sdk = {SDK_VERSION}, application = TestApplication.class)
public class SignUpFragmentTest {

    @Rule public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock SignUpPresenter mockPresenter;

    SignUpFragment signUpFragment;

    public SignUpFragmentTest() {}

    @Before
    public void setup() {
        TestApplication testApplication = (TestApplication) RuntimeEnvironment.application;
        testApplication.setComponentFactory(new TestComponentFactory());

        signUpFragment = SignUpFragment.newInstance();
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

    private class TestComponentFactory extends DefaultComponentFactory {

        @Override
        public ApplicationComponent createApplicationComponent(ToniteAliveApplication application) {
            return new ApplicationComponentSupport();
        }

        @Override
        public SignUpComponent createSignUpComponent(ApplicationComponent applicationComponent, SignUpView signUpView) {
            return new SignUpComponentSupport() {
                @Override
                public void inject(SignUpFragment fragment) {
                    fragment.presenter = mockPresenter;
                }
            };
        }

    }

}