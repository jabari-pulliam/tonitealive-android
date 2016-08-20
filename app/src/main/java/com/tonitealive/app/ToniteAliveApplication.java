package com.tonitealive.app;

import android.support.multidex.MultiDexApplication;

import com.tonitealive.app.internal.di.ComponentFactory;
import com.tonitealive.app.internal.di.DefaultComponentFactory;
import com.tonitealive.app.internal.di.components.ApplicationComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ToniteAliveApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;
    private ComponentFactory componentFactory;
    private String apiBaseUrl;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Provides access to the component factory for views to use to build their injectors.
     *
     * @return The component factory
     */
    public ComponentFactory getComponentFactory() {
        if (componentFactory == null)
            componentFactory = new DefaultComponentFactory();
        return componentFactory;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();

        apiBaseUrl = BuildConfig.SERVER_URL;
    }

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

    protected void initInjector() {
        applicationComponent = getComponentFactory().createApplicationComponent(this);
    }

    /**
     * Returns the application component.
     *
     * @return The application component
     */
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}