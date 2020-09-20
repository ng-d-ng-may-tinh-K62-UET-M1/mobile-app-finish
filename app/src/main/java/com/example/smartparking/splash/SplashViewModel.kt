package com.example.smartparking.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.smartparking.data.preference.PreferenceDataSource
import com.example.smartparking.utils.livedata.Event

class SplashViewModel @ViewModelInject constructor(
    private val preferenceDataSource: PreferenceDataSource
) : ViewModel() {
    private fun isUserOnboarded() = preferenceDataSource.getIsUserOnboarded()

    val lanchDestination = liveData {
        if (isUserOnboarded()) {
            emit(Event(SplashDestination.MAIN_ACTIVITY))
        } else {
            emit(Event(SplashDestination.ONBOARDING))
        }
    }
}

enum class SplashDestination {
    ONBOARDING,
    MAIN_ACTIVITY
}