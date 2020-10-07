package com.example.smartparking.ui.account.vehicle.list

import com.airbnb.epoxy.TypedEpoxyController
import com.example.smartparking.data.model.Vehicle

class VehicleListController : TypedEpoxyController<List<Vehicle>>() {
    override fun buildModels(data: List<Vehicle>?) {
        data?.mapIndexed { index, vehicle ->

        }
    }

}