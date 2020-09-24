package com.example.smartparking.repositories.users

import com.example.smartparking.data.model.User
import com.example.smartparking.firestore.user.FireStoreUser
import com.example.smartparking.utils.LoadState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val fireStoreUser: FireStoreUser
) : UserRepository {
    override suspend fun getUser(uid: String): Flow<LoadState<User>> = fireStoreUser.getUser(uid)

    override suspend fun addNewUser(
        uid: String,
        displayName: String,
        email: String
    ): Flow<LoadState<User>> = fireStoreUser.addNewUser(uid, displayName, email)

}