package com.tonitealive.app.internal.di.modules;

import android.app.Activity;

import com.tonitealive.app.internal.di.annotations.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return activity;
    }

}