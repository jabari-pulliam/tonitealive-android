package com.tonitealive.app;

import com.tonitealive.app.internal.di.ComponentFactory;

public class TestApplication extends ToniteAliveApplication {

    private ComponentFactory componentFactory;

    public void setComponentFactory(ComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
    }

    @Override
    public ComponentFactory getComponentFactory() {
        return componentFactory;
    }

    @Override
    protected void initInjector() {

    }
}
