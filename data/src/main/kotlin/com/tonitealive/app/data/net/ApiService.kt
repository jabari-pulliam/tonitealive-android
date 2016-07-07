package com.tonitealive.app.data.net

import com.tonitealive.app.data.model.User
import com.tonitealive.app.domain.model.AuthToken
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable


interface ApiService {

    @FormUrlEncoded
    @POST("oauth/token")
    fun login(@Field("grant_type") grantType: String = "password",
              @Field("username") username: String,
              @Field("password") password: String): Observable<AuthToken>

    @GET("logout")
    fun logout(): Observable<Void>

    @POST("v1/accounts")
    fun createAccount(username: String, email: String, password: String): Observable<User>


}