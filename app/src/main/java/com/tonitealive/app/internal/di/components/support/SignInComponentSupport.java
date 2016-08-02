package com.tonitealive.app.internal.di.components.support;

import com.tonitealive.app.domain.interactors.SignInUseCase;
import com.tonitealive.app.internal.di.components.SignInComponent;
import com.tonitealive.app.ui.presenters.signin.SignInPresenter;
import com.tonitealive.app.ui.views.signin.SignInFragment;
import com.tonitealive.app.ui.views.signin.SignInView;

public class SignInComponentSupport implements SignInComponent {
    @Override
    public void inject(SignInFragment signInFragment) {

    }

    @Override
    public SignInPresenter presenter() {
        return null;
    }

    @Override
    public SignInView view() {
        return null;
    }

    @Override
    public SignInUseCase signInUseCase() {
        return null;
    }
}
