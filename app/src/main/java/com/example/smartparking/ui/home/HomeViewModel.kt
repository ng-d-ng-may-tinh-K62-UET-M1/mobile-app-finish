package com.example.smartparking.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartparking.data.preference.PreferenceDataSource
import com.example.smartparking.utils.auth.AuthenticationManager
import com.example.smartparking.utils.livedata.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeViewModel @ViewModelInject constructor(
    private val authenticationManager: AuthenticationManager,
    private val preferenceDataSource: PreferenceDataSource
) : ViewModel() {
    private val _signOutRequest = MutableLiveData<Event<Boolean>>(Event(false))
    val signOutRequest: LiveData<Event<Boolean>>
        get() = _signOutRequest

    fun signOut() {
        Firebase.auth.signOut()
        preferenceDataSource.setIsUserOnboarded(false)
        _signOutRequest.postValue(Event(true))
    }
}