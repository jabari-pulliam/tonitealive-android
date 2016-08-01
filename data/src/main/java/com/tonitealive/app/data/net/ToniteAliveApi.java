package com.tonitealive.app.data.net;

import com.tonitealive.app.domain.model.User;

import rx.Observable;


public interface ToniteAliveApi {

    Observable<User> getUserByUsername(String username);

    Observable<User> getUserByEmail(String email);

}