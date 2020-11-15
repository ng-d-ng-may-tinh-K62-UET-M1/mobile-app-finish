package com.example.smartparking.repositories.parking

import com.example.smartparking.data.model.Parking
import com.example.smartparking.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ParkingRepository {
    suspend fun getParking(docId: String) : Flow<Resource<Parking?>>
}