package com.example.smartparking.repositories.users

import com.example.smartparking.data.model.User
import com.example.smartparking.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(uid: String): Flow<Resource<User?>>
    suspend fun addNewUser(user: User)
}