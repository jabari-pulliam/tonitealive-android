package com.tonitealive.app.internal.di.components;

import com.tonitealive.app.internal.di.annotations.PerActivity;
import com.tonitealive.app.internal.di.modules.UserProfileModule;
import com.tonitealive.app.ui.presenters.user.UserProfilePresenter;
import com.tonitealive.app.ui.views.user.UserProfileFragment;

import dagger.Component;

@PerActivity
@Component(modules = {UserProfileModule.class}, dependencies = {ApplicationComponent.class})
public interface UserProfileComponent {
    void inject(UserProfileFragment fragment);

    UserProfilePresenter userProfilePresenter();
}