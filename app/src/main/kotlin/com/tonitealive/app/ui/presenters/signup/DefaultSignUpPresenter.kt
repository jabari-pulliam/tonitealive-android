package com.tonitealive.app.ui.presenters.signup

import com.tonitealive.app.domain.interactors.SignUpUseCase
import com.tonitealive.app.internal.di.annotations.PerActivity
import com.tonitealive.app.ui.views.signup.SignUpView
import org.slf4j.LoggerFactory

@PerActivity
class DefaultSignUpPresenter(private val view: SignUpView,
                             private val signUpUseCase: SignUpUseCase) : SignUpPresenter {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun onSignUpButtonClicked() {
        logger.debug("onSignUpButtonClicked() called")
        signUpUseCase.execute(view.username, view.email, view.password).subscribe({ user ->

        }, { error ->

        })
    }

}