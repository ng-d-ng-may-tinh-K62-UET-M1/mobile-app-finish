package com.example.smartparking.ui.account

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.smartparking.data.preference.PreferenceDataSource
import com.example.smartparking.repositories.users.UserRepository
import com.example.smartparking.utils.auth.AuthenticationManager
import com.example.smartparking.utils.livedata.Event
import javax.inject.Inject

class AccountViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val preferenceDataSource: PreferenceDataSource
) : ViewModel() {

    private var _userUID: MutableLiveData<String?> = MutableLiveData(authenticationManager.getCurrentUserId())

    private val _signOutEvent = MutableLiveData<Event<Boolean>>()
    val signOutEvent: LiveData<Event<Boolean>>
        get() = _signOutEvent

    val account = Transformations.switchMap(_userUID) {
        it?.let {
            userRepository.getUser(it).asLiveData().switchMap {
                it.getLiveDataIfSuccess()
            }
        }
    }

    fun signOut() {
        authenticationManager.signOut()
        preferenceDataSource.setIsUserOnboarded(false)
        _signOutEvent.postValue(Event(true))
    }


}