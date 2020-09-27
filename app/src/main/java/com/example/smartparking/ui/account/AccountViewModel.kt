package com.example.smartparking.ui.account

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.smartparking.repositories.users.UserRepository
import com.example.smartparking.utils.auth.AuthenticationManager

class AccountViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
    authenticationManager: AuthenticationManager
) : ViewModel() {
    private var _userUID: MutableLiveData<String?> = MutableLiveData(authenticationManager.getCurrentUserId())

    val account = Transformations.switchMap(_userUID) {
        it?.let {
            userRepository.getUser(it).asLiveData().switchMap {
                it.getLiveDataIfSuccess()
            }
        }
    }


}