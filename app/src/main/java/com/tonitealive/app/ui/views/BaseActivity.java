package com.tonitealive.app.ui.views;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.tonitealive.app.ui.Navigator;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;


public abstract class BaseActivity extends AppCompatActivity {

    @Inject Navigator navigator;

    protected Navigator getNavigator() {
        checkNotNull(navigator);
        return navigator;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

}