package com.tonitealive.app.ui.views.signin;

import android.support.v4.app.Fragment;

import com.tonitealive.app.ui.views.SingleFragmentActivity;


public final class SignInActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SignInFragment.newInstance();
    }

}