package com.tonitealive.app.internal.di.components;

import com.tonitealive.app.internal.di.annotations.PerActivity;
import com.tonitealive.app.internal.di.modules.MainModule;

import dagger.Component;

@PerActivity
@Component(modules = {MainModule.class}, dependencies = {ApplicationComponent.class})
public interface MainComponent {
    //void inject(MainActivity mainActivity);

}
