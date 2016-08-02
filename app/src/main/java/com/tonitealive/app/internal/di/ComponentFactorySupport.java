package com.tonitealive.app.internal.di;

import android.app.Activity;
import android.app.Application;

import com.tonitealive.app.internal.di.components.ActivityComponent;
import com.tonitealive.app.internal.di.components.ApplicationComponent;
import com.tonitealive.app.internal.di.components.MainComponent;
import com.tonitealive.app.internal.di.components.SignInComponent;
import com.tonitealive.app.internal.di.components.SignUpComponent;
import com.tonitealive.app.internal.di.components.UserProfileComponent;
import com.tonitealive.app.ui.views.signin.SignInView;
import com.tonitealive.app.ui.views.signup.SignUpView;
import com.tonitealive.app.ui.views.user.UserProfileView;

public class ComponentFactorySupport implements ComponentFactory {
    @Override
    public ApplicationComponent createApplicationComponent(Application application) {
        return null;
    }

    @Override
    public ActivityComponent createActivityComponent(ApplicationComponent applicationComponent, Activity activity) {
        return null;
    }

    @Override
    public SignInComponent createSignInComponent(ApplicationComponent applicationComponent, SignInView signInView) {
        return null;
    }

    @Override
    public SignUpComponent createSignUpComponent(ApplicationComponent applicationComponent, SignUpView signUpView) {
        return null;
    }

    @Override
    public UserProfileComponent createUserProfileComponent(ApplicationComponent applicationComponent, UserProfileView view) {
        return null;
    }

    @Override
    public MainComponent createMainComponent(ApplicationComponent applicationComponent) {
        return null;
    }
}
