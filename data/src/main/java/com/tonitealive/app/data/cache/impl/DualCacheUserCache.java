package com.tonitealive.app.data.cache.impl;

import android.content.Context;

import com.google.common.base.Optional;
import com.tonitealive.app.data.JsonSerializer;
import com.tonitealive.app.data.cache.UserCache;
import com.tonitealive.app.domain.model.User;
import com.vincentbrison.openlibraries.android.dualcache.Builder;
import com.vincentbrison.openlibraries.android.dualcache.DualCache;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DualCacheUserCache implements UserCache {

    private static final int MAX_RAM_SIZE = 1;
    private static final int MAX_DISK_SIZE = 1000;
    private static final String KEY_CURRENT_USER = "current_user";
    private static final String CACHE_NAME = "user_cache";

    private final DualCache<User> cache;

    @Inject
    public DualCacheUserCache(Context context, JsonSerializer jsonSerializer) {
        cache = new Builder<>(CACHE_NAME, 1, User.class)
                    .enableLog()
                    .useReferenceInRam(MAX_RAM_SIZE, (v) -> 1)
                    .useSerializerInDisk(MAX_DISK_SIZE, true,
                            new DefaultCacheSerializer<>(jsonSerializer, User.class), context)
                    .build();
    }

    @Override
    public void putCurrentUser(User user) {
        cache.put(KEY_CURRENT_USER, user);
    }

    @Override
    public Optional<User> getCurrentUser() {
        return Optional.fromNullable(cache.get(KEY_CURRENT_USER));
    }

    @Override
    public void clearCurrentUser() {
        cache.delete(KEY_CURRENT_USER);
    }
}