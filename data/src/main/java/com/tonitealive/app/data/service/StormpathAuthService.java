package com.tonitealive.app.data.service;

import com.google.common.base.Optional;
import com.stormpath.sdk.Stormpath;
import com.stormpath.sdk.StormpathCallback;
import com.stormpath.sdk.models.RegisterParams;
import com.stormpath.sdk.models.StormpathError;
import com.stormpath.sdk.models.UserProfile;
import com.tonitealive.app.data.cache.UserCache;
import com.tonitealive.app.domain.model.User;
import com.tonitealive.app.domain.service.AuthService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;


@Singleton
public class StormpathAuthService implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserCache userCache;

    @Inject
    public StormpathAuthService(UserCache userCache) {
        this.userCache = userCache;
    }

    @Override
    public Observable<Void> login(String username, String password) {
        return Observable.create((sub) -> {
            Stormpath.login(username, password, new StormpathCallback<Void>() {
                @Override
                public void onSuccess(Void v) {
                    logger.info("Login succeeded");
                    if (!sub.isUnsubscribed()) {
                        sub.onNext(null);
                        sub.onCompleted();
                    }
                }

                @Override
                public void onFailure(StormpathError error) {
                    logger.error(error.developerMessage());
                    if (!sub.isUnsubscribed()) {
                        //sub.onError();
                    }
                }
            });
        });
    }

    @Override
    public void logout() {
        // Clear the cached user
        userCache.clearCurrentUser();

        Stormpath.logout();
    }

    @Override
    public Observable<Void> register(String username, String email, String password, String firstName, String lastName) {
        return Observable.create((sub) -> {
            RegisterParams params = new RegisterParams(null, null, email, password, username);
            Stormpath.register(params, new StormpathCallback<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    logger.info("Registration succeeded");
                    if (!sub.isUnsubscribed()) {
                        sub.onNext(null);
                        sub.onCompleted();
                    }
                }

                @Override
                public void onFailure(StormpathError error) {
                    logger.error(error.developerMessage());
                    if (!sub.isUnsubscribed()) {

                    }
                }
            });
        });
    }

    @Override
    public Observable<User> getLoggedInUser() {
        return Observable.create((subscriber -> {
            // Check if the user is in the cache and return the cached version if it is
            Optional<User> user = userCache.getCurrentUser();
            if (user.isPresent()) {
                subscriber.onNext(user.get());
                subscriber.onCompleted();
            } else {
                // Retrieve the user from the server
                Stormpath.getUserProfile(new StormpathCallback<UserProfile>() {
                    @Override
                    public void onSuccess(UserProfile userProfile) {
                        //subscriber.onNext(userProfile);
                    }

                    @Override
                    public void onFailure(StormpathError error) {

                    }
                });
            }
        }));
    }
}