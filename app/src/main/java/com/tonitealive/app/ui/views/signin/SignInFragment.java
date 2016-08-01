package com.tonitealive.app.ui.views.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.tonitealive.app.R;
import com.tonitealive.app.ToniteAliveApplication;
import com.tonitealive.app.internal.di.components.DaggerSignInComponent;
import com.tonitealive.app.internal.di.components.SignInComponent;
import com.tonitealive.app.internal.di.modules.SignInModule;
import com.tonitealive.app.ui.presenters.signin.SignInPresenter;
import com.tonitealive.app.ui.views.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public final class SignInFragment extends BaseFragment implements SignInView {

    @Inject SignInPresenter presenter;

    @BindView(R.id.usernameField) EditText usernameField;
    @BindView(R.id.password_field) EditText passwordField;
    @BindView(R.id.sign_in_button) Button signInButton;
    @BindView(R.id.sign_up_button) Button signUpButton;
    @BindView(R.id.forgot_password_button) Button forgotPasswordButton;
    @BindView(R.id.progress_bar) ProgressBar progressBar;


    SignInComponent component;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    private SignInComponent buildComponent() {
        ToniteAliveApplication application = (ToniteAliveApplication) getActivity().getApplication();
        return DaggerSignInComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .signInModule(new SignInModule(this))
                .build();
    }

    @Override
    public String getUsername() {
        return usernameField.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordField.getText().toString();
    }

    @Override
    public void showProgressBar() {
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (component == null)
            component = buildComponent();
        component.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.sign_in_button)
    void onSignInButtonClicked() {
        presenter.onSignInButtonClicked();
    }

    @OnClick(R.id.sign_up_button)
    void onSignUpButtonClicked() {
        presenter.onSignUpButtonClicked();
    }

    @OnClick(R.id.forgot_password_button)
    void onForgotPasswordButtonClicked() {
        presenter.onForgotPasswordButtonClicked();
    }

}