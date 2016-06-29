package com.tonitealive.app.internal.di.modules

import android.app.Activity
import com.tonitealive.app.internal.di.annotations.PerActivity
import dagger.Module
import dagger.Provides

@Module
open class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    fun provideActivity(): Activity {
        return activity
    }

}