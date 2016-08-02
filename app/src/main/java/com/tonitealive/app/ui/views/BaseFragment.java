package com.tonitealive.app.ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.tonitealive.app.ToniteAliveApplication;

public abstract class BaseFragment extends Fragment implements BaseView {

    public void showMessage(String message, MessageDuration duration) {
        int messageDuration;

        switch (duration) {
            case LONG:
                messageDuration = Toast.LENGTH_LONG;
                break;
            default:
                messageDuration = Toast.LENGTH_SHORT;
                break;
        }

        Toast toast = Toast.makeText(getActivity(), message, messageDuration);
        toast.show();
    }

    @Override
    public void showMessage(String message) {
        showMessage(message, MessageDuration.SHORT);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInjector();
    }

    protected ToniteAliveApplication getApplication() {
        return (ToniteAliveApplication) getActivity().getApplication();
    }

    protected abstract void initInjector();
}