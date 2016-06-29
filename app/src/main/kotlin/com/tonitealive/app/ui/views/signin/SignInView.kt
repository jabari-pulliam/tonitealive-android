package com.tonitealive.app.ui.views.signin


interface SignInView {
    var username: String
    var password: String

    fun showProgressBar()
    fun hideProgressBar()
}