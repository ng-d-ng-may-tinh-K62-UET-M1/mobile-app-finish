package com.example.smartparking.ui.account.vehicle.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.repositories.vehicles.VehicleRepository
import com.example.smartparking.utils.COLLECTION_VEHICLES
import com.example.smartparking.utils.auth.AuthenticationManager
import com.google.firebase.firestore.FirebaseFirestore

class VehicleListViewModel @ViewModelInject constructor (
    authenticationManager: AuthenticationManager,
    private val vehicleRepository: VehicleRepository
) : ViewModel() {
    private var vehicleListView: VehicleListView? = null

    private val _userUID = MutableLiveData<String>(authenticationManager.getCurrentUserId())

    val vehicles = Transformations.switchMap(_userUID) {
        it?.let { id ->
            vehicleRepository.getVehiclesBelongToUser(id).asLiveData()
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