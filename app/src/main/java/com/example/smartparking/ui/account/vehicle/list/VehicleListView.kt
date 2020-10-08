package com.example.smartparking.ui.account.vehicle.list

import com.example.smartparking.data.model.Vehicle

interface VehicleListView {
    fun goBack()
    fun goToVehicleForm()
    fun goToVehicleDetail(vehicle: Vehicle)
}