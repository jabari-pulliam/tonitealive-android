package com.tonitealive.app.ui.views;

import android.support.v4.app.Fragment;
import android.widget.Toast;

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
}