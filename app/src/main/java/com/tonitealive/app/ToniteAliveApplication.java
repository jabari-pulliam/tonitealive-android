package com.tonitealive.app;

import android.support.multidex.MultiDexApplication;

import com.tonitealive.app.internal.di.ComponentFactory;
import com.tonitealive.app.internal.di.DefaultComponentFactory;
import com.tonitealive.app.internal.di.components.ApplicationComponent;


public class ToniteAliveApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;
    private ComponentFactory componentFactory;

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