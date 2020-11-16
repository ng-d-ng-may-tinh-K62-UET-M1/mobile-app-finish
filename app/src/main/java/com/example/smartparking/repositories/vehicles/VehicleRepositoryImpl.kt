package com.example.smartparking.repositories.vehicles

import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.data.model.Vehicle.Companion.NAME_FIELD
import com.example.smartparking.data.model.Vehicle.Companion.PLATE_NUMBER_FIELD
import com.example.smartparking.data.model.Vehicle.Companion.UID_FIELD
import com.example.smartparking.utils.COLLECTION_VEHICLES
import com.example.smartparking.utils.Resource
import com.example.smartparking.utils.extensions.serializeToMap
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : VehicleRepository {
    override fun addVehicle(vehicle: Vehicle) {
        firebaseFirestore.collection(COLLECTION_VEHICLES).add(vehicle)
            .addOnSuccessListener { println("add vehicles success") }
            .addOnFailureListener { e -> throw  e }
    }

    @ExperimentalCoroutinesApi
    override fun getVehiclesBelongToUser(uid: String): Flow<Resource<List<Vehicle>>> =
        callbackFlow {
            offer(Resource.loading())
            val listener =
                firebaseFirestore.collection(COLLECTION_VEHICLES).whereEqualTo("uid", uid)
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
            val vehicleMap = vehicleToMap(vehicle)
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

    @ExperimentalCoroutinesApi
    override fun getFirstVehicle(uid: String): Flow<Resource<Vehicle>> = callbackFlow {
        offer(Resource.loading())
        val listener = firebaseFirestore.collection(COLLECTION_VEHICLES).whereEqualTo("uid", uid)
            .get()
            .addOnSuccessListener { documents ->
                offer(Resource.success(documents.toObjects(Vehicle::class.java).first()))
            }
            .addOnFailureListener { exception ->
                offer(Resource.error(exception.message ?: "error"))
            }
        awaitClose { cancel() }
    }

    private fun vehicleToMap(vehicle: Vehicle) : Map<String, Any?> {
        return mapOf(
            PLATE_NUMBER_FIELD to vehicle.plateNumber,
            NAME_FIELD to vehicle.name
        )
    }

}