package com.example.smartparking.ui.booking.base

import com.example.smartparking.data.model.ParkingDetailDataStore
import com.example.smartparking.data.model.Vehicle

interface BaseBookingView {
    fun syncParkingDetailToShareViewModel(parkingDetail: ParkingDetailDataStore?)
    fun syncSelectedVehicleToShareViewModel(vehicle: Vehicle?)
}