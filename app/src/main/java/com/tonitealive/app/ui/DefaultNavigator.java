package com.tonitealive.app.ui;

import android.content.Context;
import android.content.Intent;

import com.tonitealive.app.ui.views.ContentViewId;
import com.tonitealive.app.ui.views.main.MainActivity;
import com.tonitealive.app.ui.views.signin.SignInActivity;
import com.tonitealive.app.ui.views.signup.SignUpActivity;

import javax.inject.Singleton;

@Singleton
public class DefaultNavigator implements Navigator {

    private final Context context;

    public DefaultNavigator(Context context) {
        this.context = context;
    }

    public void goToSignInView() {
        Intent intent = new Intent(context, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void goToSignUpView() {
        Intent intent = new Intent(context, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void goToMainView(ContentViewId contentView) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}