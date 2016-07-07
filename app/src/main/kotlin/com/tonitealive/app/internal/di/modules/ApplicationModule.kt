package com.tonitealive.app.internal.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.preference.PreferenceManager
import com.fatboyindustrial.gsonjodatime.Converters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tonitealive.app.data.DefaultTokenStore
import com.tonitealive.app.data.GsonObjectSerializer
import com.tonitealive.app.data.ObjectSerializer
import com.tonitealive.app.data.TokenStore
import com.tonitealive.app.data.net.ApiService
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
    fun provideAuthService(): ApiService {
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
                          serializer: ObjectSerializer): TokenStore {
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
    fun provideObjectSerializer(gson: Gson): ObjectSerializer {
        return GsonObjectSerializer(gson)
    }

}