package com.tonitealive.app.internal.di.components

import android.content.Context
import android.content.SharedPreferences
import com.tonitealive.app.data.JsonSerializer
import com.tonitealive.app.data.TokenStore
import com.tonitealive.app.data.net.ApiService
import com.tonitealive.app.internal.di.modules.ApplicationModule
import com.tonitealive.app.ui.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(baseActivity: BaseActivity)

    fun context(): Context
    fun authService(): ApiService
    fun tokenStore(): TokenStore
    fun sharedPreferences(): SharedPreferences
    fun objectSerializer(): JsonSerializer
}