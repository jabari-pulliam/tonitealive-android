package com.tonitealive.app.data

import com.google.gson.Gson
import javax.inject.Singleton

@Singleton
class GsonJsonSerializer(private val gson: Gson) : JsonSerializer {
    override fun <T> toString(obj: T): String {
        return gson.toJson(obj)
    }

    override fun <T> fromString(s: String, clazz: Class<T>): T {
        return gson.fromJson(s, clazz)
    }
}