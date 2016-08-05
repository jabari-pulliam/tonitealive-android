package com.tonitealive.app.ui.views.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.tonitealive.app.R;
import com.tonitealive.app.internal.di.components.MainComponent;
import com.tonitealive.app.ui.presenters.main.MainPresenter;
import com.tonitealive.app.ui.views.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends BaseActivity implements MainView {

    public static final String EXTRA_CONTENT_VIEW = "com.tonitealive.app.content_view";

    @BindView(R.id.pager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;

    @Inject MainPresenter presenter;

    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pagerAdapter = new MainPagerAdapter(this, getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void initInjector() {
        MainComponent component = getComponentFactory().createMainComponent(getApplicationComponent());
        component.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void showContent() {

    }
}