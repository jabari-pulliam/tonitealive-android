package com.tonitealive.app.ui.views.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tonitealive.app.ui.views.user.UserProfileFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private final Context context;
    private int pageCount = 1;

    private final int VIEW_ID_PROFILE = 0;

    public MainPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return UserProfileFragment.newInstance();
    }

    @Override
    public int getCount() {
        return pageCount;
    }
}
