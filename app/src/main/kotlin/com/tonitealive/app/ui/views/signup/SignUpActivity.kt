package com.tonitealive.app.ui.views.signup

import android.support.v4.app.Fragment
import com.tonitealive.app.ui.views.SingleFragmentActivity


class SignUpActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment {
        return SignUpFragment.newInstance()
    }
}