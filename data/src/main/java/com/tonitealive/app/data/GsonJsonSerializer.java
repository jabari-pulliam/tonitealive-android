package com.tonitealive.app.data;

import com.google.gson.Gson;

import javax.inject.Singleton;

@Singleton
public class GsonJsonSerializer implements JsonSerializer {

    private final Gson gson;

    public GsonJsonSerializer(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> String toString(T obj) {
        return gson.toJson(obj);
    }

    @Override
    public <T> T fromString(String s, Class<T> clazz) {
        return gson.fromJson(s, clazz);
    }
}