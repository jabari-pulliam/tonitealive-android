package com.tonitealive.app.ui.views.signin

import android.support.v4.app.Fragment
import com.tonitealive.app.ui.views.SingleFragmentActivity


class SignInActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment {
        return SignInFragment.newInstance()
    }
}