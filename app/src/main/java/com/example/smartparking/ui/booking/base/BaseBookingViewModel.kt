package com.example.smartparking.ui.booking.base

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.smartparking.data.model.ParkingDetailDataStore
import com.example.smartparking.data.model.Vehicle

class BaseBookingViewModel @ViewModelInject constructor() : ViewModel() {
    var baseBookingView: BaseBookingView? = null

    open fun onSyncParkingDetailFromShareViewModel(parkingDetailDataStore: ParkingDetailDataStore?) {}

    open fun onSyncSelectedVehicleFromShareViewModel(selectedVehicle: Vehicle?) {}
}