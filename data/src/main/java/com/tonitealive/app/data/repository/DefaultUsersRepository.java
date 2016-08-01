package com.tonitealive.app.data.repository;

import com.tonitealive.app.data.net.ToniteAliveApi;
import com.tonitealive.app.domain.model.User;
import com.tonitealive.app.domain.model.UserProfile;
import com.tonitealive.app.domain.repositories.UsersRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.subjects.AsyncSubject;


@Singleton
public class DefaultUsersRepository implements UsersRepository {

    private final ToniteAliveApi api;

    @Inject
    public DefaultUsersRepository(ToniteAliveApi api) {
        this.api = api;
    }

    @Override
    public Observable<User> getByUsername(String username) {
        AsyncSubject<User> subject = AsyncSubject.create();
        api.getUserByUsername(username).subscribe((v) -> {
            subject.onNext(v);
            subject.onCompleted();
        }, (e) -> {
            subject.onError(e);
        });
        return subject;
    }

    @Override
    public Observable<User> getByEmail(String email) {
        AsyncSubject<User> subject = AsyncSubject.create();
        api.getUserByEmail(email).subscribe((v) -> {
            subject.onNext(v);
            subject.onCompleted();
        }, (e) -> {
            subject.onError(e);
        });

        return subject;
    }

    @Override
    public Observable<UserProfile> getProfile(String username) {
        AsyncSubject<UserProfile> subject = AsyncSubject.create();
        return subject;
    }
}