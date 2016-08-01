package com.tonitealive.app.ui.views.signin;

import com.tonitealive.app.ui.views.BaseView;


public interface SignInView extends BaseView {
    String getUsername();
    String getPassword();

    void showProgressBar();
    void hideProgressBar();
}