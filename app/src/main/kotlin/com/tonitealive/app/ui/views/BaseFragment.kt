package com.tonitealive.app.ui.views

import android.support.v4.app.Fragment
import android.widget.Toast

abstract class BaseFragment : Fragment(), BaseView {

    override fun showMessage(message: String, duration: MessageDuration) {
        var messageDuration: Int = 0
        when (duration) {
            MessageDuration.LONG -> messageDuration = Toast.LENGTH_LONG
            else -> messageDuration = Toast.LENGTH_SHORT
        }
        val toast = Toast.makeText(activity, message, messageDuration)
        toast.show()
    }
}