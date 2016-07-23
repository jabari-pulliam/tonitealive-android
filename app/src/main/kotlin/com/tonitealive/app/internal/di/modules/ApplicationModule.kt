package com.tonitealive.app.internal.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.preference.PreferenceManager
import com.fatboyindustrial.gsonjodatime.Converters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tonitealive.app.data.*
import com.tonitealive.app.data.net.RetrofitToniteAliveApi
import com.tonitealive.app.data.net.ToniteAliveApi
import com.tonitealive.app.data.repository.DefaultUsersRepository
import com.tonitealive.app.data.service.DefaultAuthService
import com.tonitealive.app.domain.executor.PostExecutionThread
import com.tonitealive.app.domain.executor.ThreadExecutor
import com.tonitealive.app.domain.repositories.UsersRepository
import com.tonitealive.app.domain.service.AuthService
import com.tonitealive.app.ui.DefaultNavigator
import com.tonitealive.app.ui.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    open fun provideNavigator(): Navigator {
        return DefaultNavigator(application.applicationContext)
    }

    @Provides
    @Singleton
    open fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    open fun provideToniteAliveApi(tokenStore: TokenStore): ToniteAliveApi {
        val info = application.packageManager.getApplicationInfo(application.packageName,
                PackageManager.GET_META_DATA)
        val apiBaseUrl = info.metaData.getString("API_BASE_URL")
        return RetrofitToniteAliveApi(apiBaseUrl, tokenStore)
    }

    @Provides
    @Singleton
    open fun provideTokenStore(sharedPreferences: SharedPreferences,
                          serializer: JsonSerializer): TokenStore {
        return DefaultTokenStore(sharedPreferences, serializer)
    }

    @Provides
    @Singleton
    open fun provideSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @Singleton
    open fun provideGson(): Gson {
        val gsonBuilder = Converters.registerAll(GsonBuilder())
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    open fun provideJsonSerializer(gson: Gson): JsonSerializer {
        return GsonJsonSerializer(gson)
    }

    @Provides
    @Singleton
    open fun provideAuthService(api: ToniteAliveApi, tokenStore: TokenStore): AuthService {
        return DefaultAuthService(api, tokenStore)
    }

    @Provides
    @Singleton
    open fun provideThreadExecutor(): ThreadExecutor {
        return JobExecutor()
    }

    @Provides
    @Singleton
    open fun providePostExecutionThread(): PostExecutionThread {
        return UiThread()
    }

    @Provides
    @Singleton
    open fun provideUsersRepository(api: ToniteAliveApi, jsonSerializer: JsonSerializer): UsersRepository {
        return DefaultUsersRepository(api, jsonSerializer)
    }
}