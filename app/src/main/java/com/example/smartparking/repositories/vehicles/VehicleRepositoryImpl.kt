package com.example.smartparking.repositories.vehicles

import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.data.model.VehicleWithOutDocumentId
import com.example.smartparking.utils.COLLECTION_VEHICLES
import com.example.smartparking.utils.Resource
import com.example.smartparking.utils.extensions.serializeToMap
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : VehicleRepository {
    override fun addVehicle(vehicle: VehicleWithOutDocumentId) {
        firebaseFirestore.collection(COLLECTION_VEHICLES).add(vehicle)
            .addOnSuccessListener { println("add vehicles success") }
            .addOnFailureListener { e -> throw  e }
    }


//    override suspend fun getVehiclesBelongToUser(uid: String): Flow<Resource<List<Vehicle>>> {
//        return flow {
//            try {
//                emit(Resource.loading())
//                firebaseFirestore.collection(COLLECTION_VEHICLES).whereEqualTo("uid", uid)
//                    .addSnapshotListener {value, _ ->
//                        if (value != null) {
//                            emit(Resource.success(value.toObjects(Vehicle::class.java)))
//                        }
//                    }
//            } catch (e: Exception) {
//                throw e
//            }
//        }
//    }

    @ExperimentalCoroutinesApi
    override fun getVehiclesBelongToUser(uid: String): Flow<Resource<List<Vehicle>>> =
        callbackFlow {
            offer(Resource.loading())
            val listener = firebaseFirestore.collection(COLLECTION_VEHICLES).whereEqualTo("uid", uid)
                .addSnapshotListener { value, error ->
                    if (value != null) {
                        offer(Resource.success(value.toObjects(Vehicle::class.java)))
                    }
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

    override fun updateVehicle(vehicle: Vehicle) {
        vehicle.documentId?.let {
            val vehicleWithOutDocumentId = vehicle.copy(documentId = null)
            val vehicleMap = vehicleWithOutDocumentId.serializeToMap()
            firebaseFirestore.collection(COLLECTION_VEHICLES).document(it).update(vehicleMap)
                .addOnSuccessListener { println("Update vehicle success") }
                .addOnFailureListener { e -> throw  e }
        }
    }

    override fun deleteVehicle(vehicle: Vehicle) {
        vehicle.documentId?.let {
            firebaseFirestore.collection(COLLECTION_VEHICLES).document(it).delete()
        }
    }

}