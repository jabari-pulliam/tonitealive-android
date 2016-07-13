package com.tonitealive.app.data.net

import com.tonitealive.app.domain.model.AuthToken
import com.tonitealive.app.domain.model.User
import retrofit2.http.*
import rx.Observable


interface ApiService {

    @FormUrlEncoded
    @POST("oauth/token")
    fun login(@Field("grant_type") grantType: String = "password",
              @Field("username") username: String,
              @Field("password") password: String): Observable<AuthToken>

    @GET("logout")
    fun logout(): Observable<Void>

    @POST("v1/users")
    fun createUser(username: String, email: String, password: String): Observable<User>

    @GET("v1/users")
    fun getUserByUsername(@Query("username") username: String): Observable<User>

    @GET("v1/users")
    fun getUserByEmail(@Query("email") email: String): Observable<User>

    @DELETE("v1/users/{username}")
    fun removeUser(@Path("username") username: String): Observable<Void>

}