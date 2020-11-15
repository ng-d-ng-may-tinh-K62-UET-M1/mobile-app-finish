package com.example.smartparking.ui.booking

import com.example.smartparking.data.model.ParkingDetailDataStore
import com.example.smartparking.data.model.Vehicle

sealed class BookingItems(val id: String) {
    data class BookingParkingDetail(
        var parkingDetail: ParkingDetailDataStore
    ) : BookingItems("parking_detail")

    data class BookingVehicle(
        var vehicle: Vehicle
    ) : BookingItems("booking_vehicle")
}