package com.tonitealive.app.ui.views.user;

import com.tonitealive.app.internal.di.components.UserProfileComponent;
import com.tonitealive.app.ui.views.BaseFragment;


public final class UserProfileFragment extends BaseFragment implements UserProfileView {

    public static UserProfileFragment newInstance() {
        return new UserProfileFragment();
    }

    @Override
    protected void initInjector() {
        UserProfileComponent component = getComponentFactory()
                .createUserProfileComponent(getApplicationComponent(), this);
        component.inject(this);
    }

}