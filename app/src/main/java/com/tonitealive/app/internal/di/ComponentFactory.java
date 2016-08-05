package com.tonitealive.app.internal.di;

import android.app.Activity;

import com.tonitealive.app.ToniteAliveApplication;
import com.tonitealive.app.internal.di.components.ActivityComponent;
import com.tonitealive.app.internal.di.components.ApplicationComponent;
import com.tonitealive.app.internal.di.components.MainComponent;
import com.tonitealive.app.internal.di.components.SignInComponent;
import com.tonitealive.app.internal.di.components.SignUpComponent;
import com.tonitealive.app.internal.di.components.UserProfileComponent;
import com.tonitealive.app.ui.views.signin.SignInView;
import com.tonitealive.app.ui.views.signup.SignUpView;
import com.tonitealive.app.ui.views.user.UserProfileView;

public interface ComponentFactory {

    ApplicationComponent createApplicationComponent(ToniteAliveApplication application);

    ActivityComponent createActivityComponent(ApplicationComponent applicationComponent, Activity activity);

    SignInComponent createSignInComponent(ApplicationComponent applicationComponent, SignInView signInView);

    SignUpComponent createSignUpComponent(ApplicationComponent applicationComponent, SignUpView signUpView);

    UserProfileComponent createUserProfileComponent(ApplicationComponent applicationComponent, UserProfileView view);

    MainComponent createMainComponent(ApplicationComponent applicationComponent);

}
