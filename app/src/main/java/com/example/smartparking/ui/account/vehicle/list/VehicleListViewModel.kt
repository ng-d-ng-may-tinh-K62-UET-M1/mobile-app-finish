package com.example.smartparking.ui.account.vehicle.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.smartparking.repositories.vehicles.VehicleRepository
import com.example.smartparking.utils.auth.AuthenticationManager

class VehicleListViewModel @ViewModelInject constructor(
    authenticationManager: AuthenticationManager,
    private val vehicleRepository: VehicleRepository
) : ViewModel() {
    private var vehicleListView: VehicleListView? = null

    private val _userUID = MutableLiveData<String>(authenticationManager.getCurrentUserId())

    val vehicles = Transformations.switchMap(_userUID) {
        it?.let { id ->
            vehicleRepository.getVehiclesBelongToUser(id).asLiveData().switchMap {
                it.getLiveDataIfSuccess()
            }
        }
    }

    fun setView(vehicleListView: VehicleListView) {
        this.vehicleListView = vehicleListView
    }

    fun goBack() {
        vehicleListView?.goBack()
    }

    fun goToVehicleForm() {
        vehicleListView?.goToVehicleForm()
    }
}