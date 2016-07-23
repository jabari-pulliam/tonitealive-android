package com.tonitealive.app.data.net

import com.google.gson.GsonBuilder
import com.tonitealive.app.data.TokenStore
import com.tonitealive.app.domain.model.AuthToken
import com.tonitealive.app.domain.model.User
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import javax.inject.Singleton

@Singleton
class RetrofitToniteAliveApi(private val baseUrl: String,
                             private val tokenStore: TokenStore) : ToniteAliveApi {

    private val api = buildApiService(buildClient())

    private fun buildApiService(client: OkHttpClient): ApiService {
        // Register Gson modules
        val gsonBuilder = GsonBuilder()
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build()
        return retrofit.create(ApiService::class.java)
    }

    private fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
                    .addInterceptor(ApiAuthInterceptor(tokenStore))
                    .build()
    }

    override fun login(grantType: String, username: String, password: String): Observable<AuthToken> {
        return api.login(grantType, username, password)
    }

    override fun logout(): Observable<Void> {
        return api.logout()
    }

    override fun createUser(username: String, email: String, password: String): Observable<User> {
        return api.createUser(username, email, password)
    }

    override fun getUserByUsername(username: String): Observable<User> {
        return api.getUserByUsername(username)
    }

    override fun getUserByEmail(email: String): Observable<User> {
        return api.getUserByEmail(email)
    }

    override fun removeUser(username: String): Observable<Void> {
        return api.removeUser(username)
    }
}