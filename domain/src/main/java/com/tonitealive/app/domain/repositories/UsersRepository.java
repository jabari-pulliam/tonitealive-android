package com.tonitealive.app.domain.repositories;

import com.tonitealive.app.domain.model.User;
import com.tonitealive.app.domain.model.UserProfile;

import rx.Observable;

public interface UsersRepository {

    Observable<User> getByUsername(String username);

    Observable<User> getByEmail(String email);

    Observable<UserProfile> getProfile(String username);

}