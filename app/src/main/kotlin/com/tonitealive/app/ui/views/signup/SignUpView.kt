package com.tonitealive.app.ui.views.signup

import com.tonitealive.app.ui.views.BaseView


interface SignUpView : BaseView {
    val username: String
    val email: String
    val password: String
    val confirmPassword: String

    fun showProgressDialog()
    fun hideProgressDialog()
}