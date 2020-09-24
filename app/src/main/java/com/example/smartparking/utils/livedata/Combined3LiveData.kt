package com.example.smartparking.utils.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import java.lang.UnsupportedOperationException

class Combined3LiveData <T1, T2, T3, S>(
    source1: LiveData<T1>,
    source2: LiveData<T2>,
    source3: LiveData<T3>,
    private val combine: (data1: T1?, data2: T2?, data3: T3?) -> S
) : MediatorLiveData<S>() {
    private var data1: T1? = null
    private var data2: T2? = null
    private var data3: T3? = null
    init {
        super.addSource(source1) {
            data1 = it
            value = combine(data1, data2, data3)
        }
        super.addSource(source2) {
            data2 = it
            value = combine(data1, data2, data3)
        }
        super.addSource(source3) {
            data3 = it
            value = combine(data1, data2, data3)
        }
    }

    override fun <S : Any?> addSource(source: LiveData<S>, onChanged: Observer<in S>) {
        throw UnsupportedOperationException()
    }

    override fun <S : Any?> removeSource(toRemote: LiveData<S>) {
        throw UnsupportedOperationException()
    }
}