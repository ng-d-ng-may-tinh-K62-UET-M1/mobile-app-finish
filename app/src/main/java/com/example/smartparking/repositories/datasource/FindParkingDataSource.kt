package com.example.smartparking.repositories.datasource

import com.example.smartparking.data.request.FindParkingRequest
import com.example.smartparking.repositories.findparking.FindParkingService
import javax.inject.Inject

class FindParkingDataSource @Inject constructor(private val findParkingService: FindParkingService) :
    BaseDataSource() {
    suspend fun getParkingListAvailable(request: FindParkingRequest) =
        getResult { findParkingService.getParkingListAvailable(request) }
}