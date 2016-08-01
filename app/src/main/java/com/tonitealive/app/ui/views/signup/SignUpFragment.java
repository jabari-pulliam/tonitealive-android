package com.tonitealive.app.ui.views.signup;

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
import com.tonitealive.app.internal.di.components.DaggerSignUpComponent;
import com.tonitealive.app.internal.di.components.SignUpComponent;
import com.tonitealive.app.internal.di.modules.SignUpModule;
import com.tonitealive.app.ui.presenters.signup.SignUpPresenter;
import com.tonitealive.app.ui.views.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public final class SignUpFragment extends BaseFragment implements SignUpView {

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @BindView(R.id.usernameField) EditText usernameField;
    @BindView(R.id.emailField) EditText emailField;
    @BindView(R.id.passwordField) EditText passwordField;
    @BindView(R.id.confirmPasswordField) EditText confirmPasswordField;
    @BindView(R.id.signUpButton) Button signUpButton;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Inject SignUpPresenter presenter;

    private SignUpComponent component;

    void setComponent(SignUpComponent component) {
        this.component = component;
    }

    private SignUpComponent buildComponent() {
        ToniteAliveApplication application = (ToniteAliveApplication) getActivity().getApplication();
        return DaggerSignUpComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .signUpModule(new SignUpModule(this))
                .build();
    }

    @Override
    public String getUsername() {
        return usernameField.getText().toString();
    }

    @Override
    public String getEmail() {
        return emailField.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordField.getText().toString();
    }

    @Override
    public String getConfirmPassword() {
        return confirmPasswordField.getText().toString();
    }

    @Override
    public void showProgressDialog() {
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressDialog() {
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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.signUpButton)
    void onSignInButtonClicked() {
        presenter.onSignUpButtonClicked();
    }
}