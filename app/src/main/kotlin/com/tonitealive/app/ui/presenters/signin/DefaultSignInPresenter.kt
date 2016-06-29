package com.tonitealive.app.ui.presenters.signin

import com.tonitealive.app.ui.views.signin.SignInView


class DefaultSignInPresenter(val view: SignInView) : SignInPresenter {

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