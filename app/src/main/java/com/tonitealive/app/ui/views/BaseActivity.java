package com.tonitealive.app.ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tonitealive.app.ToniteAliveApplication;
import com.tonitealive.app.internal.di.ComponentFactory;
import com.tonitealive.app.internal.di.components.ApplicationComponent;


public abstract class BaseActivity extends AppCompatActivity {

    protected ComponentFactory getComponentFactory() {
        ToniteAliveApplication application = (ToniteAliveApplication) getApplication();
        return application.getComponentFactory();
    }

    protected ApplicationComponent getApplicationComponent() {
        ToniteAliveApplication application = (ToniteAliveApplication) getApplication();
        return application.getApplicationComponent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInjector();
    }

    protected abstract void initInjector();

}