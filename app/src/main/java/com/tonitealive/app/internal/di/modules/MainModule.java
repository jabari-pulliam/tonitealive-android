package com.tonitealive.app.internal.di.modules;

import com.tonitealive.app.internal.di.annotations.PerActivity;
import com.tonitealive.app.ui.presenters.main.DefaultMainPresenter;
import com.tonitealive.app.ui.presenters.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @PerActivity @Provides
    public MainPresenter provideMainPresenter(DefaultMainPresenter mainPresenter) {
        return mainPresenter;
    }

}
