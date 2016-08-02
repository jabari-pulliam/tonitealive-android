package com.tonitealive.app.ui.views.user;

import com.tonitealive.app.internal.di.ComponentFactory;
import com.tonitealive.app.internal.di.components.ApplicationComponent;
import com.tonitealive.app.internal.di.components.UserProfileComponent;
import com.tonitealive.app.ui.views.BaseFragment;


public final class UserProfileFragment extends BaseFragment implements UserProfileView {

    public static UserProfileFragment newInstance() {
        return new UserProfileFragment();
    }

    @Override
    protected void initInjector() {
        ApplicationComponent applicationComponent = getApplication().getApplicationComponent();
        ComponentFactory componentFactory = getApplication().getComponentFactory();
        UserProfileComponent component = componentFactory.createUserProfileComponent(applicationComponent, this);
        component.inject(this);
    }

}