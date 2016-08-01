package com.tonitealive.app.data;


public interface JsonSerializer {

    <T> String toString(T obj);

    <T> T fromString(String s, Class<T> clazz);

}