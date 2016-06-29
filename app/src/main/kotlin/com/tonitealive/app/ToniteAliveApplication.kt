package com.tonitealive.app

import android.app.Application
import com.tonitealive.app.internal.di.components.ApplicationComponent
import com.tonitealive.app.internal.di.components.DaggerApplicationComponent
import com.tonitealive.app.internal.di.modules.ApplicationModule


class ToniteAliveApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}