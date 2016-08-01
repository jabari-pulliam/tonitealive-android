package com.tonitealive.app.ui.presenters.signin;

import com.tonitealive.app.domain.interactors.SignInUseCase;
import com.tonitealive.app.internal.di.annotations.PerActivity;
import com.tonitealive.app.ui.Navigator;
import com.tonitealive.app.ui.views.signin.SignInView;

@PerActivity
public class DefaultSignInPresenter implements SignInPresenter {

    private final SignInView view;
    private final SignInUseCase signInUseCase;
    private final Navigator navigator;

    public DefaultSignInPresenter(SignInView signInView, SignInUseCase signInUseCase, Navigator navigator) {
        this.view = signInView;
        this.signInUseCase = signInUseCase;
        this.navigator = navigator;
    }

    @Override
    public void onSignInButtonClicked() {
        view.showProgressBar();
        signInUseCase.execute(view.getUsername(), view.getPassword()).subscribe(result -> {
            if (result) {
                view.showMessage("Sign In successful");
            }
        },  error -> {
            view.showMessage("An error occurred.");
        });
    }

    @Override
    public void onSignUpButtonClicked() {
        navigator.goToSignUpView();
    }

    @Override
    public void onForgotPasswordButtonClicked() {

    }
}