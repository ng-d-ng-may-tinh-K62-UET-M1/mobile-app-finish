package com.example.smartparking.repositories.vehicles

import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.utils.COLLECTION_VEHICLES
import com.example.smartparking.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : VehicleRepository {
    override suspend fun addVehicle(vehicle: Vehicle) {
        firebaseFirestore.collection(COLLECTION_VEHICLES).add(vehicle)
            .addOnSuccessListener { println("add vehicles success") }
            .addOnFailureListener { e -> throw  e }
    }

    @ExperimentalCoroutinesApi
    override fun getVehiclesBelongToUser(uid: String): Flow<Resource<List<Vehicle>>> =
        callbackFlow {
            offer(Resource.loading())
            firebaseFirestore.collection(COLLECTION_VEHICLES).whereEqualTo("uid", uid)
                .addSnapshotListener { value, error ->
                    if (value != null) {
                        offer(Resource.success(value.toObjects(Vehicle::class.java)))
                    }
                }
        }

}