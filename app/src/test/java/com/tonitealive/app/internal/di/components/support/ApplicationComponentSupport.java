package com.tonitealive.app.internal.di.components.support;

import android.content.Context;
import android.content.SharedPreferences;

import com.tonitealive.app.data.JsonSerializer;
import com.tonitealive.app.data.TokenStore;
import com.tonitealive.app.data.cache.UserCache;
import com.tonitealive.app.data.net.ToniteAliveApi;
import com.tonitealive.app.domain.executor.PostExecutionThread;
import com.tonitealive.app.domain.executor.ThreadExecutor;
import com.tonitealive.app.domain.repositories.UsersRepository;
import com.tonitealive.app.domain.service.AuthService;
import com.tonitealive.app.internal.di.components.ApplicationComponent;
import com.tonitealive.app.ui.Navigator;

public class ApplicationComponentSupport implements ApplicationComponent {

    @Override
    public Context context() {
        return null;
    }

    @Override
    public Navigator navigator() {
        return null;
    }

    @Override
    public ThreadExecutor threadExecutor() {
        return null;
    }

    @Override
    public PostExecutionThread postExecutionThread() {
        return null;
    }

    @Override
    public ToniteAliveApi toniteAliveApi() {
        return null;
    }

    @Override
    public TokenStore tokenStore() {
        return null;
    }

    @Override
    public SharedPreferences sharedPreferences() {
        return null;
    }

    @Override
    public JsonSerializer jsonSerializer() {
        return null;
    }

    @Override
    public UsersRepository usersRepository() {
        return null;
    }

    @Override
    public AuthService authService() {
        return null;
    }

    @Override
    public UserCache userCache() {
        return null;
    }
}
