package com.example.smartparking.repositories.booking

import com.example.smartparking.data.model.Booking
import com.example.smartparking.utils.COLLECTION_BOOKING_LIST
import com.example.smartparking.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class BookingRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : BookingRepository {
    @ExperimentalCoroutinesApi
    override fun getBookingBelongToUserId(uid: String): Flow<Resource<List<Booking>>> = callbackFlow {
        offer(Resource.loading())
        val listener = firebaseFirestore.collection(COLLECTION_BOOKING_LIST).whereEqualTo("uid", uid)
            .addSnapshotListener { value, error ->
                if (value != null) offer(Resource.success(value.toObjects(Booking::class.java)))
                error?.let {
                    offer(Resource.error(it.message.toString()))
                    cancel(it.message.toString())
                }
            }
        awaitClose {
            listener.remove()
            cancel()
        }
    }

}