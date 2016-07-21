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
import com.tonitealive.app.data.net.ApiService
import com.tonitealive.app.data.repository.DefaultUsersRepository
import com.tonitealive.app.data.service.DefaultAuthService
import com.tonitealive.app.domain.executor.PostExecutionThread
import com.tonitealive.app.domain.executor.ThreadExecutor
import com.tonitealive.app.domain.repositories.UsersRepository
import com.tonitealive.app.domain.service.AuthService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val info = application.packageManager.getApplicationInfo(application.packageName,
                PackageManager.GET_META_DATA)
        val apiBaseUrl = info.metaData.getString("API_BASE_URL")

        // Register Gson modules
        val gsonBuilder = GsonBuilder()
        val retrofit = Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTokenStore(sharedPreferences: SharedPreferences,
                          serializer: JsonSerializer): TokenStore {
        return DefaultTokenStore(sharedPreferences, serializer)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = Converters.registerAll(GsonBuilder())
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideJsonSerializer(gson: Gson): JsonSerializer {
        return GsonJsonSerializer(gson)
    }

    @Provides
    @Singleton
    fun provideAuthService(apiService: ApiService, tokenStore: TokenStore): AuthService {
        return DefaultAuthService(apiService, tokenStore)
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(): ThreadExecutor {
        return JobExecutor()
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(): PostExecutionThread {
        return UiThread()
    }

    @Provides
    @Singleton
    fun provideUsersRepository(apiService: ApiService, jsonSerializer: JsonSerializer): UsersRepository {
        return DefaultUsersRepository(apiService, jsonSerializer)
    }
}