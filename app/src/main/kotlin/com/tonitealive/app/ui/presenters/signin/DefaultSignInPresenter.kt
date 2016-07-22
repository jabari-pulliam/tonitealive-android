package com.tonitealive.app.ui.presenters.signin

import com.tonitealive.app.domain.interactors.SignInUseCase
import com.tonitealive.app.internal.di.annotations.PerActivity
import com.tonitealive.app.ui.Navigator
import com.tonitealive.app.ui.views.signin.SignInView

@PerActivity
class DefaultSignInPresenter(private val view: SignInView,
                             private val signInUseCase: SignInUseCase,
                             private val navigator: Navigator) : SignInPresenter {

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
        navigator.goToSignUpView()
    }

    override fun onForgotPasswordButtonClicked() {
        throw UnsupportedOperationException()
    }
}