package com.tonitealive.app.ui.views;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.tonitealive.app.internal.di.components.ApplicationComponent;
import com.tonitealive.app.internal.di.modules.ActivityModule;

import static com.google.common.base.Preconditions.checkNotNull;


public abstract class BaseActivity extends AppCompatActivity {

    protected ApplicationComponent applicationComponent;
    protected final ActivityModule activityModule = new ActivityModule(this);

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        checkNotNull(applicationComponent);
        applicationComponent.inject(this);
    }

}