package com.tonitealive.app.data.cache.impl;

import com.tonitealive.app.data.JsonSerializer;
import com.vincentbrison.openlibraries.android.dualcache.CacheSerializer;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class DefaultCacheSerializer<T> implements CacheSerializer<T> {

    private final JsonSerializer jsonSerializer;
    private final Class<T> clazz;

    public DefaultCacheSerializer(JsonSerializer jsonSerializer, Class<T> clazz) {
        checkNotNull(jsonSerializer);
        checkNotNull(clazz);
        this.jsonSerializer = jsonSerializer;
        this.clazz = clazz;
    }

    @Override
    public T fromString(String data) {
        checkNotNull(data);
        checkState(!data.isEmpty());
        return jsonSerializer.fromString(data, clazz);
    }

    @Override
    public String toString(T object) {
        checkNotNull(object);
        return jsonSerializer.toString(object);
    }
}