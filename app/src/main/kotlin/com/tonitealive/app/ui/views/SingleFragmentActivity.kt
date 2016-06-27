package com.tonitealive.app.ui.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.tonitealive.app.R


abstract class SingleFragmentActivity : FragmentActivity() {

    protected abstract  fun createFragment(): Fragment

    protected fun getLayoutResId(): Int {
        return R.id.fragment_container;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        var fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = createFragment()
            supportFragmentManager.beginTransaction()
                                    .add(R.id.fragment_container, fragment)
                                    .commit()
        }
    }
}