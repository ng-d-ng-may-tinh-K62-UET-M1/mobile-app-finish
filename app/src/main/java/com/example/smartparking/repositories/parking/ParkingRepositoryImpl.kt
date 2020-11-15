package com.example.smartparking.repositories.parking

import com.example.smartparking.data.model.Parking
import com.example.smartparking.utils.COLLECTION_PARKING_LIST
import com.example.smartparking.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ParkingRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ParkingRepository {
    @ExperimentalCoroutinesApi
    override suspend fun getParking(docId: String): Flow<Resource<Parking?>> = callbackFlow {
        offer(Resource.loading())
        val listener = firestore.collection(COLLECTION_PARKING_LIST).document(docId)
            .addSnapshotListener { snapshot, error ->
                if (snapshot != null && snapshot.exists()) {
                    offer(Resource.success(snapshot.toObject(Parking::class.java)))
                }
                error?.let {
                    offer(Resource.error(it.message.toString()))
                }
            }
        awaitClose {
            listener.remove()
            cancel()
        }
    }
}