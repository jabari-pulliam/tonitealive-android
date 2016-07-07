package com.tonitealive.app.ui.presenters.signin

import com.tonitealive.app.internal.di.annotations.PerActivity
import com.tonitealive.app.ui.views.signin.SignInView

@PerActivity
class DefaultSignInPresenter(private val view: SignInView) : SignInPresenter {


    override fun onSignInButtonClicked() {

        throw UnsupportedOperationException()
    }

    override fun onSignUpButtonClicked() {
        throw UnsupportedOperationException()
    }

    override fun onForgotPasswordButtonClicked() {
        throw UnsupportedOperationException()
    }
}