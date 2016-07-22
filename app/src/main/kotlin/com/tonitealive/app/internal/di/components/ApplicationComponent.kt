package com.tonitealive.app.internal.di.components

import android.content.Context
import android.content.SharedPreferences
import com.tonitealive.app.data.JsonSerializer
import com.tonitealive.app.data.TokenStore
import com.tonitealive.app.data.net.ApiService
import com.tonitealive.app.domain.executor.PostExecutionThread
import com.tonitealive.app.domain.executor.ThreadExecutor
import com.tonitealive.app.domain.repositories.UsersRepository
import com.tonitealive.app.domain.service.AuthService
import com.tonitealive.app.internal.di.modules.ApplicationModule
import com.tonitealive.app.ui.Navigator
import com.tonitealive.app.ui.views.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(baseActivity: BaseActivity)

    fun context(): Context
    fun navigator(): Navigator
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun apiService(): ApiService
    fun tokenStore(): TokenStore
    fun sharedPreferences(): SharedPreferences
    fun jsonSerializer(): JsonSerializer
    fun usersRepository(): UsersRepository
    fun authService(): AuthService
}