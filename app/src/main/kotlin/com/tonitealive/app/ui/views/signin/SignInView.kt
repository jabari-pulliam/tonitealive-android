package com.tonitealive.app.ui.views.signin

import com.tonitealive.app.ui.views.BaseView


interface SignInView : BaseView {
    var username: String
    var password: String

    fun showProgressBar()
    fun hideProgressBar()
}