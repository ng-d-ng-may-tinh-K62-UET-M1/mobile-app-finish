package com.example.smartparking.firestore.user

import com.example.smartparking.utils.LoadState
import kotlinx.coroutines.flow.Flow
import com.example.smartparking.data.model.User

interface FireStoreUser {
    suspend fun getUser(uid: String): Flow<LoadState<User>>
    suspend fun addNewUser(uid: String, displayName: String, email: String): Flow<LoadState<User>>
}