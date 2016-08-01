package com.tonitealive.app.data.net;

import com.tonitealive.app.domain.model.User;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


interface ApiService {

    @GET("v1/users/{username}")
    Observable<User> getUserByUsername(@Path("username") String username);

    @GET("v1/users")
    Observable<User> getUserByEmail(@Query("email") String email);

}