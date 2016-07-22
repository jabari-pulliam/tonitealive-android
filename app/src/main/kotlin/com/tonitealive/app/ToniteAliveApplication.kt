package com.tonitealive.app

import android.support.multidex.MultiDexApplication
import com.tonitealive.app.internal.di.components.ApplicationComponent
import com.tonitealive.app.internal.di.components.DaggerApplicationComponent
import com.tonitealive.app.internal.di.modules.ApplicationModule


class ToniteAliveApplication : MultiDexApplication() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}