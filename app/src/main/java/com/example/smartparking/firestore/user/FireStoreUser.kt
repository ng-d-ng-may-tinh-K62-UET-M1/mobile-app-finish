package com.example.smartparking.firestore.user

import com.example.smartparking.utils.State
import com.firebase.ui.auth.data.model.User
import kotlinx.coroutines.flow.Flow



interface FireStoreUser {
    fun getUser(uid: String): Flow<State<User>>
    fun addNewUser(displayName: String, email: String): Flow<State<User>>
}