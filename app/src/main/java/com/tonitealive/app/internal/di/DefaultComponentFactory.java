package com.tonitealive.app.internal.di;

import android.app.Activity;
import android.app.Application;

import com.tonitealive.app.internal.di.components.ActivityComponent;
import com.tonitealive.app.internal.di.components.ApplicationComponent;
import com.tonitealive.app.internal.di.components.DaggerActivityComponent;
import com.tonitealive.app.internal.di.components.DaggerApplicationComponent;
import com.tonitealive.app.internal.di.components.DaggerMainComponent;
import com.tonitealive.app.internal.di.components.DaggerSignInComponent;
import com.tonitealive.app.internal.di.components.DaggerSignUpComponent;
import com.tonitealive.app.internal.di.components.DaggerUserProfileComponent;
import com.tonitealive.app.internal.di.components.MainComponent;
import com.tonitealive.app.internal.di.components.SignInComponent;
import com.tonitealive.app.internal.di.components.SignUpComponent;
import com.tonitealive.app.internal.di.components.UserProfileComponent;
import com.tonitealive.app.internal.di.modules.ActivityModule;
import com.tonitealive.app.internal.di.modules.ApplicationModule;
import com.tonitealive.app.internal.di.modules.SignInModule;
import com.tonitealive.app.internal.di.modules.SignUpModule;
import com.tonitealive.app.internal.di.modules.UserProfileModule;
import com.tonitealive.app.ui.views.signin.SignInView;
import com.tonitealive.app.ui.views.signup.SignUpView;
import com.tonitealive.app.ui.views.user.UserProfileView;

public final class DefaultComponentFactory implements ComponentFactory {

    @Override
    public ApplicationComponent createApplicationComponent(Application application) {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .build();
    }

    @Override
    public ActivityComponent createActivityComponent(ApplicationComponent applicationComponent, Activity activity) {
        return DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(activity))
                .build();
    }

    @Override
    public SignInComponent createSignInComponent(ApplicationComponent applicationComponent, SignInView signInView) {
        return DaggerSignInComponent.builder()
                .applicationComponent(applicationComponent)
                .signInModule(new SignInModule(signInView))
                .build();
    }

    @Override
    public SignUpComponent createSignUpComponent(ApplicationComponent applicationComponent, SignUpView signUpView) {
        return DaggerSignUpComponent.builder()
                .applicationComponent(applicationComponent)
                .signUpModule(new SignUpModule(signUpView))
                .build();
    }

    @Override
    public UserProfileComponent createUserProfileComponent(ApplicationComponent applicationComponent, UserProfileView view) {
        return DaggerUserProfileComponent.builder()
                .applicationComponent(applicationComponent)
                .userProfileModule(new UserProfileModule(view))
                .build();
    }

    @Override
    public MainComponent createMainComponent(ApplicationComponent applicationComponent) {
        return DaggerMainComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
    }
}
