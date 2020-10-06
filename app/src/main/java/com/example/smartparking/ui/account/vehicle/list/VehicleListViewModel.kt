package com.example.smartparking.ui.account.vehicle.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class VehicleListViewModel @ViewModelInject constructor () : ViewModel() {
    private var vehicleListView: VehicleListView? = null

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