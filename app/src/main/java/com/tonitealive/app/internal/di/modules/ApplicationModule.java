package com.tonitealive.app.internal.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tonitealive.app.ToniteAliveApplication;
import com.tonitealive.app.data.GsonJsonSerializer;
import com.tonitealive.app.data.JobExecutor;
import com.tonitealive.app.data.JsonSerializer;
import com.tonitealive.app.data.StormpathTokenStore;
import com.tonitealive.app.data.TokenStore;
import com.tonitealive.app.data.UiThread;
import com.tonitealive.app.data.cache.UserCache;
import com.tonitealive.app.data.cache.impl.DualCacheUserCache;
import com.tonitealive.app.data.net.RetrofitToniteAliveApi;
import com.tonitealive.app.data.net.ToniteAliveApi;
import com.tonitealive.app.data.repository.DefaultUsersRepository;
import com.tonitealive.app.data.service.StormpathAuthService;
import com.tonitealive.app.domain.executor.PostExecutionThread;
import com.tonitealive.app.domain.executor.ThreadExecutor;
import com.tonitealive.app.domain.repositories.UsersRepository;
import com.tonitealive.app.domain.service.AuthService;
import com.tonitealive.app.ui.DefaultNavigator;
import com.tonitealive.app.ui.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final ToniteAliveApplication application;

    public ApplicationModule(ToniteAliveApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Navigator provideNavigator() {
        return new DefaultNavigator(application.getApplicationContext());
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    public ToniteAliveApi provideToniteAliveApi(TokenStore tokenStore) {
        return new RetrofitToniteAliveApi(application.getApiBaseUrl(), tokenStore);
    }

    @Provides
    @Singleton
    public TokenStore provideTokenStore() {
        return new StormpathTokenStore();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = Converters.registerAll(new GsonBuilder());
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public JsonSerializer provideJsonSerializer(Gson gson) {
        return new GsonJsonSerializer(gson);
    }

    @Provides
    @Singleton
    public AuthService provideAuthService(UserCache userCache) {
        return new StormpathAuthService(application, userCache, application.getApiBaseUrl());
    }

    @Provides
    @Singleton
    public ThreadExecutor provideThreadExecutor() {
        return new JobExecutor();
    }

    @Provides
    @Singleton
    public PostExecutionThread providePostExecutionThread() {
        return new UiThread();
    }

    @Provides
    @Singleton
    public UsersRepository provideUsersRepository(DefaultUsersRepository repo) {
        return repo;
    }

    @Provides
    @Singleton
    public UserCache provideUserCache(DualCacheUserCache cache) {
        return cache;
    }
}