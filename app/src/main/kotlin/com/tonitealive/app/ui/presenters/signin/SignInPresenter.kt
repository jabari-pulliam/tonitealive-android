package com.tonitealive.app.ui.presenters.signin

import com.tonitealive.app.internal.di.annotations.PerActivity

@PerActivity
interface SignInPresenter {
    fun onSignInButtonClicked()
    fun onSignUpButtonClicked()
    fun onForgotPasswordButtonClicked()
}