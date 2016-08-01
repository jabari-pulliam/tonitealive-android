package com.tonitealive.app.ui.presenters.signup;

import com.tonitealive.app.domain.interactors.SignUpUseCase;
import com.tonitealive.app.internal.di.annotations.PerActivity;
import com.tonitealive.app.ui.views.signup.SignUpView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PerActivity
public final class DefaultSignUpPresenter implements SignUpPresenter {

    private final SignUpView view;
    private final SignUpUseCase signUpUseCase;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DefaultSignUpPresenter(SignUpView view, SignUpUseCase signUpUseCase) {
        this.view = view;
        this.signUpUseCase = signUpUseCase;
    }

    @Override
    public void onSignUpButtonClicked() {
        logger.debug("onSignUpButtonClicked() called");
        signUpUseCase.execute(view.getUsername(), view.getEmail(), view.getPassword()).subscribe( user -> {

        }, error -> {

        });
    }

}