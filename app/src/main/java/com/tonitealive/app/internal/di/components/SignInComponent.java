package com.tonitealive.app.internal.di.components;

import com.tonitealive.app.domain.interactors.SignInUseCase;
import com.tonitealive.app.internal.di.annotations.PerActivity;
import com.tonitealive.app.internal.di.modules.SignInModule;
import com.tonitealive.app.ui.presenters.signin.SignInPresenter;
import com.tonitealive.app.ui.views.signin.SignInFragment;
import com.tonitealive.app.ui.views.signin.SignInView;

import dagger.Component;

@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {SignInModule.class})
public interface SignInComponent {
    void inject(SignInFragment signInFragment);

    SignInPresenter presenter();
    SignInView view();
    SignInUseCase signInUseCase();
}