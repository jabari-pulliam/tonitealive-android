package com.tonitealive.app.ui.views.user;

import android.support.v4.app.Fragment;

import com.tonitealive.app.ui.views.SingleFragmentActivity;


public final class UserProfileActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return UserProfileFragment.newInstance();
    }

}