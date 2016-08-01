package com.tonitealive.app.data.net;

import com.google.gson.GsonBuilder;
import com.tonitealive.app.data.TokenStore;
import com.tonitealive.app.domain.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

@Singleton
public class RetrofitToniteAliveApi implements ToniteAliveApi {

    private final String baseUrl;
    private final TokenStore tokenStore;
    private final ApiService api;

    @Inject
    public RetrofitToniteAliveApi(String baseUrl, TokenStore tokenStore) {
        this.baseUrl = baseUrl;
        this.tokenStore = tokenStore;

        api = buildApiService(buildClient());
    }

    private ApiService buildApiService(OkHttpClient client) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Retrofit retrofit = new Retrofit.Builder()
                                .client(client)
                                .baseUrl(baseUrl)
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                                .build();
        return retrofit.create(ApiService.class);
    }

    private OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ApiAuthInterceptor(tokenStore))
                .build();
    }

    @Override
    public Observable<User> getUserByUsername(String username) {
        return api.getUserByUsername(username);
    }

    @Override
    public Observable<User> getUserByEmail(String email) {
        return api.getUserByEmail(email);
    }
}