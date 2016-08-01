package com.tonitealive.app.internal.di.modules;

import com.tonitealive.app.internal.di.annotations.PerActivity;
import com.tonitealive.app.ui.presenters.user.DefaultUserProfilePresenter;
import com.tonitealive.app.ui.presenters.user.UserProfilePresenter;
import com.tonitealive.app.ui.views.user.UserProfileView;

import dagger.Module;
import dagger.Provides;

@Module
public class UserProfileModule {

    private final UserProfileView view;

    public UserProfileModule(UserProfileView view) {
        this.view = view;
    }

    @Provides @PerActivity
    public UserProfilePresenter provideUserProfilePresenter() {
        return new DefaultUserProfilePresenter();
    }

}