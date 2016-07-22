package com.tonitealive.app.ui

import android.content.Context
import android.content.Intent
import com.tonitealive.app.ui.views.signin.SignInActivity
import com.tonitealive.app.ui.views.signup.SignUpActivity
import javax.inject.Singleton

@Singleton
class DefaultNavigator(private val context: Context) : Navigator {

    override fun goToSignInView() {
        val intent = Intent(context, SignInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    override fun goToSignUpView() {
        val intent = Intent(context, SignUpActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}