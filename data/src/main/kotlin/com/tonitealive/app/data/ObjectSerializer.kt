package com.tonitealive.app.data


interface ObjectSerializer {

    fun <T> toString(obj: T): String

    fun <T> fromString(s: String, clazz: Class<T>): T

}