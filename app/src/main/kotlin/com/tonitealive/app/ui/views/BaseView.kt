package com.tonitealive.app.ui.views


interface BaseView {
    fun showMessage(message: String, duration: MessageDuration = MessageDuration.SHORT)
}