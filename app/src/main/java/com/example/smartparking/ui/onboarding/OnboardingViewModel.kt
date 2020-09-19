package com.example.smartparking.ui.onboarding

import android.content.Intent
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartparking.data.preference.PreferenceDataSource
import com.example.smartparking.utils.AuthenticationManager
import com.example.smartparking.utils.livedata.Event

class OnboardingViewModel @ViewModelInject constructor(
    private val authenticationManager: AuthenticationManager,
    private val preferenceDataSource: PreferenceDataSource
) : ViewModel() {
    private val _performSignInEvent = MutableLiveData<Event<Boolean>>()
    val performSignInEvent: LiveData<Event<Boolean>>
        get() = _performSignInEvent

    fun signIn() {
        _performSignInEvent.postValue(Event(true))
    }

}