package com.tonitealive.app.ui.presenters.signin

import com.tonitealive.app.domain.interactors.SignInUseCase
import com.tonitealive.app.internal.di.annotations.PerActivity
import com.tonitealive.app.ui.views.signin.SignInView

@PerActivity
class DefaultSignInPresenter(private val view: SignInView,
                             private val signInUseCase: SignInUseCase) : SignInPresenter {

    override fun onSignInButtonClicked() {
        view.showProgressBar()
        signInUseCase.execute(view.username, view.password).subscribe({ result ->
            if (result) {
                view.showMessage("Sign In successful")
            }
        }, { error ->
            view.showMessage("An error occurred.")
        })
    }

    override fun onSignUpButtonClicked() {
        throw UnsupportedOperationException()
    }

    override fun onForgotPasswordButtonClicked() {
        throw UnsupportedOperationException()
    }
}