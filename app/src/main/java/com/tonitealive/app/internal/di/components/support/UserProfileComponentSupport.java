package com.tonitealive.app.internal.di.components.support;

import com.tonitealive.app.internal.di.components.UserProfileComponent;
import com.tonitealive.app.ui.presenters.user.UserProfilePresenter;
import com.tonitealive.app.ui.views.user.UserProfileFragment;

public class UserProfileComponentSupport implements UserProfileComponent {
    @Override
    public void inject(UserProfileFragment fragment) {

    }

    @Override
    public UserProfilePresenter userProfilePresenter() {
        return null;
    }
}
