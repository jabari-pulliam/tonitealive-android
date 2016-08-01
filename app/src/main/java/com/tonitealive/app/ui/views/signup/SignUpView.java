package com.tonitealive.app.ui.views.signup;

import com.tonitealive.app.ui.views.BaseView;


public interface SignUpView extends BaseView {
    String getUsername();
    String getEmail();
    String getPassword();
    String getConfirmPassword();

    void showProgressDialog();
    void hideProgressDialog();
}