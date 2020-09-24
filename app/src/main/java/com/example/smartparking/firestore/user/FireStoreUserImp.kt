package com.example.smartparking.firestore.user

import com.example.smartparking.data.model.User
import com.example.smartparking.utils.COLLECTION_USERS
import com.example.smartparking.utils.LoadState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FireStoreUserImp @Inject constructor(
    private val firestore: FirebaseFirestore
) : FireStoreUser {
    override suspend fun getUser(uid: String): Flow<LoadState<User>> = callbackFlow {
        offer(LoadState.Loading)
        firestore.collection(COLLECTION_USERS).document(uid).addSnapshotListener { snapshot, e ->
            if (e != null) offer(LoadState.Error(e))
            if (snapshot == null) {
                offer(LoadState.Loaded(null))
            } else {
                offer(LoadState.Loaded(snapshot.toObject(User::class.java)))
            }
        }
        awaitClose { cancel() }
    }

    override suspend fun addNewUser(
        uid: String,
        displayName: String,
        email: String
    ): Flow<LoadState<User>> = callbackFlow {
        val user = hashMapOf(
            DISPLAY_NAME to displayName,
            EMAIL to email
        )
        offer(LoadState.Loading)
        firestore.collection(COLLECTION_USERS).document(uid).set(user)
            .addOnSuccessListener { offer(LoadState.Loaded(User.fromHashMap(user))) }
            .addOnFailureListener { e -> offer(LoadState.Error(e)) }
    }

    companion object {
        const val DISPLAY_NAME = "display_name"
        const val EMAIL = "email"
    }


}
