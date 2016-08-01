package com.tonitealive.app.data.net;

import com.google.common.base.Optional;
import com.tonitealive.app.data.TokenStore;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class ApiAuthInterceptor implements Interceptor {

    private final TokenStore tokenStore;

    public ApiAuthInterceptor(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Optional<String> token = tokenStore.getAuthToken();
        if (token.isPresent()) {
            request = request.newBuilder()
                        .addHeader("Authorization", "Bearer " + token.get())
                        .build();
        }
        return chain.proceed(request);
    }
}
