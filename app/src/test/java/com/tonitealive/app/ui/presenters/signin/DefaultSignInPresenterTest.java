package com.tonitealive.app.ui.presenters.signin;

import com.tonitealive.app.data.TokenStore;
import com.tonitealive.app.domain.executor.PostExecutionThread;
import com.tonitealive.app.domain.executor.ThreadExecutor;
import com.tonitealive.app.domain.interactors.SignInUseCase;
import com.tonitealive.app.domain.service.AuthService;
import com.tonitealive.app.ui.Navigator;
import com.tonitealive.app.ui.views.signin.SignInView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import rx.Observable;


public class DefaultSignInPresenterTest {

    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock SignInView mockView;
    @Mock TokenStore mockTokenStore;
    @Mock Navigator mockNavigator;
    @Mock ThreadExecutor mockExecutor;
    @Mock PostExecutionThread mockPostExecutionThread;
    @Mock AuthService mockAuthService;

    SignInUseCase signInUseCase;
    DefaultSignInPresenter presenter;

    @Before
    public void setup() {
        signInUseCase = new SignInUseCase(mockExecutor, mockPostExecutionThread, mockAuthService);
        presenter = new DefaultSignInPresenter(mockView, signInUseCase, mockNavigator);
    }

    @Test
    public void onSignInButtonClicked_showProgressBar() {
        // With
        String username = "username";
        String password = "password";

        // When
        Mockito.when(mockView.getUsername()).thenReturn(username);
        Mockito.when(mockView.getPassword()).thenReturn(password);
        Mockito.when(mockAuthService.login(username, password)).thenReturn(Observable.create(subscriber -> {
            subscriber.onNext(null);
            subscriber.onCompleted();
        }));
        presenter.onSignInButtonClicked();

        // Then
        Mockito.verify(mockView).showProgressBar();
    }

    @Test
    public void onSignUpButtonClicked_navigateToSignUpView() {
        // When
        presenter.onSignUpButtonClicked();

        // Then
        Mockito.verify(mockNavigator).goToSignUpView();
    }

}