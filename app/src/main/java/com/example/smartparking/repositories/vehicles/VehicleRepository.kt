package com.example.smartparking.repositories.vehicles

import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.data.model.VehicleWithOutDocumentId
import com.example.smartparking.utils.Resource
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    fun addVehicle(vehicle: VehicleWithOutDocumentId)
    fun getVehiclesBelongToUser(uid: String) : Flow<Resource<List<Vehicle>>>
    fun updateVehicle(vehicle: Vehicle)
    fun deleteVehicle(vehicle: Vehicle)
}