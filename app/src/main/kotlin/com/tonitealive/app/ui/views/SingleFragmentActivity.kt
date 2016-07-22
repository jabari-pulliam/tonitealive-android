package com.tonitealive.app.ui.views

import android.os.Bundle
import android.support.v4.app.Fragment
import com.tonitealive.app.R


abstract class SingleFragmentActivity : BaseActivity() {

    protected abstract  fun createFragment(): Fragment

    protected fun getLayoutResId(): Int {
        return R.layout.activity_fragment;
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