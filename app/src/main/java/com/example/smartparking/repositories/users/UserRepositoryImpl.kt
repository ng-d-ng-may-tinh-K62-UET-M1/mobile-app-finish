package com.example.smartparking.repositories.users

import com.example.smartparking.data.model.User
import com.example.smartparking.utils.COLLECTION_USERS
import com.example.smartparking.utils.Resource
import com.example.smartparking.utils.extensions.serializeToMap
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {
    @ExperimentalCoroutinesApi
    override fun getUser(uid: String): Flow<Resource<User?>> = callbackFlow {
        offer(Resource.loading())
        firestore.collection(COLLECTION_USERS).document(uid).addSnapshotListener { snapshot, e ->
            if (snapshot != null && snapshot.exists()) {
                offer(Resource.success(snapshot.toObject(User::class.java)))
            }
        }
        awaitClose { cancel() }
    }

    override suspend fun addNewUser(
        user: User
    ) {
        user.uid?.let {
            firestore.collection(COLLECTION_USERS).document(it).set(user)
                .addOnCompleteListener {}
                .addOnFailureListener { e -> throw e }
        }
    }

    override suspend fun updateUser(user: User) {
        user.uid?.let {
            val userMap = user.serializeToMap()
            firestore.collection(COLLECTION_USERS).document(it).update(userMap)
                .addOnSuccessListener {  }
                .addOnFailureListener { e -> throw e }
        }
    }

}