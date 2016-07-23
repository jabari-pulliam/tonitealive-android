package com.tonitealive.app.data.net

import com.tonitealive.app.data.TokenStore
import okhttp3.Interceptor
import okhttp3.Response


internal class ApiAuthInterceptor(private val tokenStore: TokenStore) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        var request = chain!!.request()
        val authToken = tokenStore.authToken
        if (authToken != null) {
            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer ${authToken.accessToken}")
                    .build()
        }
        return chain.proceed(request)
    }

}