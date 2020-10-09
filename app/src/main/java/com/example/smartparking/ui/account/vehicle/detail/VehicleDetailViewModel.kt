package com.example.smartparking.ui.account.vehicle.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.repositories.vehicles.VehicleRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    fun setVehiclePlateNumber(value: String) {
        _vehicle.value = _vehicle.value?.copy(plateNumber = value)
    }

    fun setVehicleName(value: String) {
        _vehicle.value = _vehicle.value?.copy(name = value)
    }

    fun updateVehicle() {
        _vehicle.value?.let {
            vehicleRepository.updateVehicle(vehicle = it)
        }
        goBack()
    }

    fun deleteVehicle() {
        _vehicle.value?.let {
            vehicleRepository.deleteVehicle(it)
        }
        goBack()
    }

    fun setData(vehicle: Vehicle) {
        _vehicle.postValue(vehicle)
    }

    fun goBack() {
        vehicleDetailView?.goBack()
    }
}