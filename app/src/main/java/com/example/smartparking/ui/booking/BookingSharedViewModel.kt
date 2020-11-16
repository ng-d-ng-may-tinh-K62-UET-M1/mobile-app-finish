package com.example.smartparking.ui.booking

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartparking.data.model.ParkingDetailDataStore
import com.example.smartparking.data.model.Vehicle

class BookingSharedViewModel @ViewModelInject constructor() : ViewModel() {
    private val _parkingDetail = MutableLiveData<ParkingDetailDataStore>()
    val parkingDetailDataStore: LiveData<ParkingDetailDataStore>
        get() = _parkingDetail

    private val _selectedVehicle = MutableLiveData<Vehicle>()
    val selectedVehicle: LiveData<Vehicle>
        get() = _selectedVehicle

    fun setParkingDetail(parkingDetailDataStore: ParkingDetailDataStore?) {
        if (parkingDetailDataStore != _parkingDetail.value) _parkingDetail.value = parkingDetailDataStore
    }

    fun setSelectedVehicle(vehicle: Vehicle?) {
        if (vehicle != _selectedVehicle.value) _selectedVehicle.value = vehicle
    }

    fun reset() {
        _parkingDetail.value = null
        _selectedVehicle.value = null
    }
}