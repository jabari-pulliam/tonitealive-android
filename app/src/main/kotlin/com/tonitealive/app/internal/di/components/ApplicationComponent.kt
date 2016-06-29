package com.tonitealive.app.internal.di.components

import android.content.Context
import com.tonitealive.api.AuthService
import com.tonitealive.app.internal.di.modules.ApplicationModule
import com.tonitealive.app.ui.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(baseActivity: BaseActivity)

    fun context(): Context
    fun authService(): AuthService
}