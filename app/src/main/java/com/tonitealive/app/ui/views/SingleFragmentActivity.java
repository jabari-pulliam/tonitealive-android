package com.tonitealive.app.ui.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.tonitealive.app.R;


public abstract class SingleFragmentActivity extends BaseActivity {

    protected abstract Fragment createFragment();

    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            getSupportFragmentManager().beginTransaction()
                                    .add(R.id.fragment_container, fragment)
                                    .commit();
        }
    }

    @Override
    protected void initInjector() {
        // No op
    }
}