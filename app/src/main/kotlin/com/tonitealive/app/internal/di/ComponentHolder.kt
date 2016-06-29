package com.tonitealive.app.internal.di

import kotlin.reflect.KProperty

class ComponentHolder<T>(private val factory: () -> T) {

    var holder: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (holder == null)
            holder = factory()
        return holder!!
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        holder = value
    }
}