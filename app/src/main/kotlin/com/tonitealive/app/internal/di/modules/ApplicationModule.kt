package com.tonitealive.app.internal.di.modules

import android.app.Application
import android.content.Context
import com.tonitealive.api.AuthService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Module
open class ApplicationModule(private val application: Application) {

    @Provides
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    fun provideAuthService(): AuthService {
        val retrofit = Retrofit.Builder()
                            .baseUrl("https://api.tonitealive.com")
                            .addConverterFactory(JacksonConverterFactory.create())
                            .build()
        return retrofit.create(AuthService::class.java)
    }

}