package com.example.smartparking.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

data class Resource<T>(val status: Status, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS,
        LOADING,
        ERROR
    }
    fun getDataIfSuccess() : T? = if (status == Status.SUCCESS) data else null

    fun getLiveDataIfSuccess() = liveData {
        if (status == Status.SUCCESS) {
            emit(data)
        }
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null) : Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }
}