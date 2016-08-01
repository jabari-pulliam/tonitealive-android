package com.tonitealive.app.data;

import android.content.Context;

import com.tonitealive.app.domain.MessageId;
import com.tonitealive.app.domain.MessageProvider;

import java.util.HashMap;
import java.util.Map;

public class DefaultMessageProvider implements MessageProvider {

    private final Context context;
    private final Map<MessageId, Integer> messageCodes = new HashMap<>();

    public DefaultMessageProvider(Context context) {
        this.context = context;
        initMessageCodes();
    }

    @Override
    public String getMessage(MessageId id) {
        Integer code = messageCodes.get(id);
        if (code != null)
            return context.getString(code);
        throw new IllegalArgumentException("No messsage found for id " + id);
    }

    private void initMessageCodes() {
        messageCodes.put(MessageId.SIGN_IN_SUCCESS, R.string.signin_succeeded);
    }
}