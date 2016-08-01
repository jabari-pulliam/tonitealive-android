package com.tonitealive.app;

import android.support.multidex.MultiDexApplication;

import com.tonitealive.app.internal.di.components.ApplicationComponent;
import com.tonitealive.app.internal.di.components.DaggerApplicationComponent;
import com.tonitealive.app.internal.di.modules.ApplicationModule;


public final class ToniteAliveApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}