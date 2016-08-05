package com.tonitealive.app.internal.di.components.support;

import com.tonitealive.app.internal.di.components.SignUpComponent;
import com.tonitealive.app.ui.presenters.signup.SignUpPresenter;
import com.tonitealive.app.ui.views.signup.SignUpFragment;

public class SignUpComponentSupport implements SignUpComponent {
    @Override
    public void inject(SignUpFragment fragment) {

    }

    @Override
    public SignUpPresenter presenter() {
        return null;
    }
}
