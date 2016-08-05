package com.tonitealive.app.ui.presenters.signin;

import com.tonitealive.app.internal.di.annotations.PerActivity;

@PerActivity
public interface SignInPresenter {
    void onResume();
    void onSignInButtonClicked();
    void onSignUpButtonClicked();
    void onForgotPasswordButtonClicked();
}