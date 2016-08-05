package com.tonitealive.app.data.service;

import android.content.Context;

import com.google.common.base.Optional;
import com.stormpath.sdk.Stormpath;
import com.stormpath.sdk.StormpathCallback;
import com.stormpath.sdk.StormpathConfiguration;
import com.stormpath.sdk.models.RegisterParams;
import com.stormpath.sdk.models.StormpathError;
import com.stormpath.sdk.models.UserProfile;
import com.tonitealive.app.data.cache.UserCache;
import com.tonitealive.app.data.exception.NetworkConnectionException;
import com.tonitealive.app.domain.model.User;
import com.tonitealive.app.domain.service.AuthService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

import static com.google.common.base.Preconditions.checkState;


@Singleton
public class StormpathAuthService implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserCache userCache;
    private final String baseUrl;

    @Inject
    public StormpathAuthService(Context context, UserCache userCache, String baseUrl) {
        this.userCache = userCache;
        this.baseUrl = baseUrl;

        StormpathConfiguration config = new StormpathConfiguration.Builder()
                .baseUrl(baseUrl)
                .build();
        Stormpath.init(context, config);
    }

    @Override
    public Observable<Void> login(String username, String password) {
        checkState(Stormpath.isInitialized(), "Stormpath must be initialized");
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
        checkState(Stormpath.isInitialized(), "Stormpath must be initialized");

        // Clear the cached user
        userCache.clearCurrentUser();

        Stormpath.logout();
    }

    @Override
    public Observable<Void> register(String username, String email, String password, String firstName, String lastName) {
        checkState(Stormpath.isInitialized(), "Stormpath must be initialized");
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
        checkState(Stormpath.isInitialized(), "Stormpath must be initialized");
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
                        logger.info("Found user with username: " + userProfile.getUsername());
                        if (!subscriber.isUnsubscribed()) {
                            User user = User.create(userProfile.getUsername(), userProfile.getEmail());
                            subscriber.onNext(user);
                            subscriber.onCompleted();
                        }
                    }

                    @Override
                    public void onFailure(StormpathError error) {
                        logger.error(error.developerMessage());
                        if (!subscriber.isUnsubscribed()) {
                            Throwable exception = error.throwable();
                            if (exception instanceof IllegalStateException) {
                                // This means the user is not logged in so we will return null instead of an exception
                                subscriber.onNext(null);
                                subscriber.onCompleted();
                            } else {
                                subscriber.onError(new NetworkConnectionException(error.message()));
                            }
                        }
                    }
                });
            }
        }));
    }
}