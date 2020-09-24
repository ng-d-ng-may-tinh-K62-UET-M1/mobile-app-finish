package com.example.smartparking.repositories.users

import com.example.smartparking.data.model.User
import com.example.smartparking.utils.LoadState
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(uid: String): Flow<LoadState<User>>
    suspend fun addNewUser(uid: String, displayName: String, email: String): Flow<LoadState<User>>
}