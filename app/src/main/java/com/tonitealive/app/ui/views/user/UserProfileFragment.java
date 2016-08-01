package com.tonitealive.app.ui.views.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tonitealive.app.ToniteAliveApplication;
import com.tonitealive.app.internal.di.components.DaggerUserProfileComponent;
import com.tonitealive.app.internal.di.components.UserProfileComponent;
import com.tonitealive.app.internal.di.modules.UserProfileModule;
import com.tonitealive.app.ui.views.BaseFragment;


public final class UserProfileFragment extends BaseFragment implements UserProfileView {

    private UserProfileComponent component;

    void setComponent(UserProfileComponent component) {
        this.component = component;
    }

    private UserProfileComponent buildComponent() {
        ToniteAliveApplication application = (ToniteAliveApplication) getActivity().getApplication();
        return DaggerUserProfileComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .userProfileModule(new UserProfileModule(this))
                .build();
    }

    public static UserProfileFragment newInstance() {
        return new UserProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (component == null)
            component = buildComponent();

        component.inject(this);
    }
}