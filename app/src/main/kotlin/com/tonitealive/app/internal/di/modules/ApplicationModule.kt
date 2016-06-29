package com.tonitealive.app.internal.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
open class ApplicationModule(private val application: Application) {

    @Provides
    fun provideApplicationContext(): Context {
        return application
    }



}