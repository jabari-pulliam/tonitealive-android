package com.tonitealive.app.ui;


import com.tonitealive.app.ui.views.ContentViewId;

public interface Navigator {
    void goToSignInView();
    void goToSignUpView();
    void goToMainView(ContentViewId contentView);
}