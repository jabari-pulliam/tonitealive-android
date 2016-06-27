package com.tonitealive.app.ui.views.signin

import android.support.v4.app.Fragment
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import com.tonitealive.app.R
import com.tonitealive.app.ui.presenters.SignInPresenter


class SignInFragment : Fragment() {

    private lateinit var presenter: SignInPresenter

    @BindView(R.id.username_field)
    lateinit var usernameField: EditText

    @BindView(R.id.password_field)
    lateinit var passwordField: EditText

    @BindView(R.id.sign_in_button)
    lateinit var signInButton: Button

    @BindView(R.id.sign_up_button)
    lateinit var signUpButton: Button

    @BindView(R.id.forgot_password_button)
    lateinit var forgotPasswordButton: Button


    companion object {
        fun newInstance(): SignInFragment {
            return SignInFragment()
        }
    }



}