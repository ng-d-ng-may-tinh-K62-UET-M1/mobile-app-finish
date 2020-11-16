package com.example.smartparking.ui.booking.vehicles

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.smartparking.repositories.vehicles.VehicleRepository
import com.example.smartparking.ui.account.vehicle.list.VehicleListView
import com.example.smartparking.utils.auth.AuthenticationManager

class BookingVehicleViewModel @ViewModelInject constructor(
    authenticationManager: AuthenticationManager,
    private val vehicleRepository: VehicleRepository
) : ViewModel() {
    private var bookingVehicleListView: BookingVehicleListView? = null

    private val _userUID = MutableLiveData<String>(authenticationManager.getCurrentUserId())

    val vehicles = Transformations.switchMap(_userUID) {
        it?.let { id ->
            vehicleRepository.getVehiclesBelongToUser(id).asLiveData().switchMap {
                it.getLiveDataIfSuccess()
            }
        }
    }

    fun setView(bookingVehicleListView: BookingVehicleListView) {
        this.bookingVehicleListView = bookingVehicleListView
    }

    fun goBack() {
        bookingVehicleListView?.goBack()
    }
}