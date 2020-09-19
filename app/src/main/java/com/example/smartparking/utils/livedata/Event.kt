package com.example.smartparking.utils.livedata

import androidx.lifecycle.Observer

open class Event <out T>(private val content: T) {
    var hasBeenHanled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHanled) {
            null
        } else {
            hasBeenHanled = true
            content
        }
    }

    fun peekContent(): T = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(t: Event<T>?) {
        t?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}