package com.example.smartparking.ui.onboarding

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartparking.data.model.User
import com.example.smartparking.repositories.users.UserRepository
import com.example.smartparking.utils.COLLECTION_USERS
import com.example.smartparking.utils.LoadState
import com.example.smartparking.utils.livedata.Event
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnboardingViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val fireStore: FirebaseFirestore
) : ViewModel() {
    private val _performSignInEvent = MutableLiveData<Event<Boolean>>()
    val performSignInEvent: LiveData<Event<Boolean>>
        get() = _performSignInEvent

    fun signIn() {
        _performSignInEvent.postValue(Event(true))
    }

    fun createFirebaseUser(uid: String?, displayName: String?, email: String?) {
        println("From viewModel")
        println("$uid, $displayName, $email")
        uid?.let {
            displayName?.let {
                email?.let {
//                    val user = User(uid, displayName, email)
//                    fireStore.collection(COLLECTION_USERS).document(uid).set(user)
//                        .addOnSuccessListener {  }
//                        .addOnFailureListener { e -> throw e }
                    // TODO debug again
                }
            }
        }
    }
}