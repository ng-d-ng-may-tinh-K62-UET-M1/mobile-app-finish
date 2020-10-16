package com.example.smartparking.ui.account.vehicle.form

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.repositories.vehicles.VehicleRepository
import com.example.smartparking.utils.auth.AuthenticationManager

class VehicleFormViewModel @ViewModelInject constructor(
    private val vehicleRepository: VehicleRepository,
    authenticationManager: AuthenticationManager
) : ViewModel() {
    private val currentUid = authenticationManager.getCurrentUserId()
    private var vehicleFormView: VehicleFormView? = null


    private val _vehicle = MutableLiveData<Vehicle>(Vehicle())
    val vehicle: LiveData<Vehicle>
        get() = _vehicle

    fun setVehiclePlate(value: String) {
        _vehicle.value = _vehicle.value?.copy(plateNumber = value)
    }

    fun setVehicleName(value: String) {
        _vehicle.value = _vehicle.value?.copy(name = value)
    }

    fun setVehicleFormView(vehicleFormView: VehicleFormView) {
        this.vehicleFormView = vehicleFormView
    }

    fun goBack() {
        vehicleFormView?.goBack()
    }

    fun addNewVehicle() {
        val vehicleToRequest = _vehicle.value
        vehicleToRequest?.uid = currentUid
        vehicleToRequest?.let { vehicle ->
            vehicleRepository.addVehicle(vehicle)
        }
        goBack()
    }
}