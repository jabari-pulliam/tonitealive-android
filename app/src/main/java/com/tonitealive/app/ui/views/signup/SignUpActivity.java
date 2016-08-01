package com.tonitealive.app.ui.views.signup;

import android.support.v4.app.Fragment;

import com.tonitealive.app.ui.views.SingleFragmentActivity;


public final class SignUpActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SignUpFragment.newInstance();
    }

}