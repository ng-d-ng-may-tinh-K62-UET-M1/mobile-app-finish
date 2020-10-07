package com.example.smartparking.repositories.vehicles

import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.utils.Resource
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    suspend fun addVehicle(vehicle: Vehicle)
    fun getVehiclesBelongToUser(uid: String) : Flow<Resource<List<Vehicle>>>
}