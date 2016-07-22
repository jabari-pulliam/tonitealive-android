package com.tonitealive.app.ui.views.signup

import android.os.Bundle
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
import com.tonitealive.app.internal.di.components.DaggerSignUpComponent
import com.tonitealive.app.internal.di.components.SignUpComponent
import com.tonitealive.app.internal.di.modules.SignUpModule
import com.tonitealive.app.ui.presenters.signup.SignUpPresenter
import com.tonitealive.app.ui.views.BaseFragment
import javax.inject.Inject


class SignUpFragment : BaseFragment(), SignUpView {

    companion object {
        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }

    @BindView(R.id.usernameField) lateinit var usernameField: EditText
    @BindView(R.id.emailField) lateinit var emailField: EditText
    @BindView(R.id.passwordField) lateinit var passwordField: EditText
    @BindView(R.id.confirmPasswordField) lateinit var confirmPasswordField: EditText
    @BindView(R.id.signUpButton) lateinit var signUpButton: Button
    @BindView(R.id.progressBar) lateinit var progressBar: ProgressBar

    @Inject lateinit var presenter: SignUpPresenter

    var component by ComponentHolder<SignUpComponent>({
        val myApp = activity.application as ToniteAliveApplication
        DaggerSignUpComponent.builder()
            .applicationComponent(myApp.applicationComponent)
            .signUpModule(SignUpModule(this))
            .build()
    })

    override val username: String
        get() = usernameField.text.toString()
    override val email: String
        get() = emailField.text.toString()
    override val password: String
        get() = passwordField.text.toString()
    override val confirmPassword: String
        get() = confirmPasswordField.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_sign_up, container, false)
        ButterKnife.bind(this, view!!)
        return view
    }

    override fun showProgressDialog() {
        progressBar.isIndeterminate = true
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressDialog() {
        progressBar.visibility = View.GONE
    }

    @OnClick(R.id.signUpButton)
    fun onSignInButtonClicked() {
        presenter.onSignUpButtonClicked()
    }
}