package com.tonitealive.app.internal.di.components;

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
import com.tonitealive.app.internal.di.modules.ApplicationModule;
import com.tonitealive.app.ui.Navigator;
import com.tonitealive.app.ui.views.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context context();
    Navigator navigator();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    ToniteAliveApi toniteAliveApi();
    TokenStore tokenStore();
    SharedPreferences sharedPreferences();
    JsonSerializer jsonSerializer();
    UsersRepository usersRepository();
    AuthService authService();
    UserCache userCache();
}
