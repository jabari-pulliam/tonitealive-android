package com.tonitealive.app.ui.views.signin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.tonitealive.app.R
import com.tonitealive.app.ToniteAliveApplication
import com.tonitealive.app.internal.di.ComponentHolder
import com.tonitealive.app.internal.di.components.DaggerSignInComponent
import com.tonitealive.app.internal.di.components.SignInComponent
import com.tonitealive.app.internal.di.modules.SignInModule
import com.tonitealive.app.ui.presenters.signin.SignInPresenter
import javax.inject.Inject


class SignInFragment : Fragment(), SignInView {

    @Inject
    lateinit var presenter: SignInPresenter

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

    @BindView(R.id.progress_bar)
    lateinit var progressBar: ProgressBar

    var component by ComponentHolder<SignInComponent>({
        val myApplication = activity.application as ToniteAliveApplication
        DaggerSignInComponent.builder()
                .applicationComponent(myApplication.applicationComponent)
                .signInModule(SignInModule(this))
                .build()
    })

    override var username: String
        get() = usernameField.text.toString()
        set(value) { usernameField.setText(value) }

    override var password: String
        get() = passwordField.text.toString()
        set(value) { passwordField.setText(value) }

    companion object {
        fun newInstance(): SignInFragment {
            return SignInFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjector()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_sign_in, container, false)
        if (view != null)
            ButterKnife.bind(this, view)
        return view
    }

    @OnClick(R.id.sign_in_button)
    fun onSignInButtonClicked() {
        presenter.onSignInButtonClicked()
    }

    @OnClick(R.id.sign_up_button)
    fun onSignUpButtonClicked() {
        presenter.onSignUpButtonClicked()
    }

    @OnClick(R.id.forgot_password_button)
    fun onForgotPasswordButtonClicked() {
        presenter.onForgotPasswordButtonClicked()
    }

    override fun showProgressBar() {
        progressBar.isIndeterminate = true
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun initInjector() {
        component.inject(this)
    }

}