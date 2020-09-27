package com.example.smartparking.ui.onboarding

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartparking.data.model.User
import com.example.smartparking.repositories.users.UserRepository
import com.example.smartparking.utils.livedata.Event
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnboardingViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _performSignInEvent = MutableLiveData<Event<Boolean>>()
    val performSignInEvent: LiveData<Event<Boolean>>
        get() = _performSignInEvent

    fun signIn() {
        _performSignInEvent.postValue(Event(true))
    }

    fun createFirebaseUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addNewUser(user)
        }
    }
}