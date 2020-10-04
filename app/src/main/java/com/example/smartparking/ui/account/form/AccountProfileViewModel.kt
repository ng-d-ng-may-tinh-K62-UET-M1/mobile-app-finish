package com.example.smartparking.ui.account.form

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartparking.data.model.User
import com.example.smartparking.repositories.users.UserRepository
import com.example.smartparking.utils.livedata.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountProfileViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private var accountProfileView: AccountProfileView? = null

    private val _profile = MutableLiveData<User>()
    val profile: LiveData<User>
        get() = _profile

    fun setView(accountProfileView: AccountProfileView) {
        this.accountProfileView = accountProfileView
    }

    fun setData(user: User) {
        _profile.value = user
    }

    fun setProfileName(name: String) {
        _profile.value = _profile.value?.copy(name = name)
    }

    fun setProfilePhone(phone: String) {
        _profile.value = _profile.value?.copy(phoneNumber = phone)
    }

    fun updateUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(_profile.value!!)
            goBack()
        }
    }

    fun goBack() {
        accountProfileView?.goBack()
    }
}