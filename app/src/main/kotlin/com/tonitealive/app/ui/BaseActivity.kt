package com.tonitealive.app.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.tonitealive.app.ToniteAliveApplication
import com.tonitealive.app.internal.di.ComponentHolder
import com.tonitealive.app.internal.di.modules.ActivityModule


abstract class BaseActivity : AppCompatActivity() {

    protected val applicationComponent by ComponentHolder {
        (application as ToniteAliveApplication).applicationComponent
    }

    protected val activityModule: ActivityModule
        get() = ActivityModule(this)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        applicationComponent.inject(this)
    }


}