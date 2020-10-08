package com.example.smartparking.ui.account.vehicle.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.repositories.vehicles.VehicleRepository

class VehicleDetailViewModel @ViewModelInject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {
    private var vehicleDetailView: VehicleDetailView? = null

    private val _vehicle = MutableLiveData<Vehicle>(Vehicle())
    val vehicle: LiveData<Vehicle>
        get() = _vehicle

    fun setVehicleDetailView(vehicleDetailView: VehicleDetailView) {
        this.vehicleDetailView = vehicleDetailView
    }

    fun setData(vehicle: Vehicle) {
        _vehicle.postValue(vehicle)
    }

    fun goBack() {
        vehicleDetailView?.goBack()
    }
}