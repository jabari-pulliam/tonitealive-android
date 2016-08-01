package com.tonitealive.app.internal.di.components;

import com.tonitealive.app.internal.di.annotations.PerActivity;
import com.tonitealive.app.internal.di.modules.SignUpModule;
import com.tonitealive.app.ui.presenters.signup.SignUpPresenter;
import com.tonitealive.app.ui.views.signup.SignUpFragment;

import dagger.Component;

@PerActivity
@Component(modules = {SignUpModule.class}, dependencies = {ApplicationComponent.class})
public interface SignUpComponent {
    void inject(SignUpFragment fragment);

    SignUpPresenter presenter();
}