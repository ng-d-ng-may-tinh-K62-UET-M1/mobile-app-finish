package com.example.smartparking.ui.account.vehicle.list

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.ui.account.vehicle.list.models.VehicleInListModel_

class VehicleListController : TypedEpoxyController<List<Vehicle>>() {
    override fun buildModels(data: List<Vehicle>?) {
        data?.mapIndexed { index, vehicle ->
            VehicleInListModel_()
                .id("vehicle_item_$index")
                .vehicle(vehicle)
                .clickListener(View.OnClickListener {
                    callbacks?.onClickVehicleInList(index, vehicle)
                })
                .addTo(this)
        }
    }

    var callbacks: VehicleInListCallbacks? = null

    interface VehicleInListCallbacks {
        fun onClickVehicleInList(
            index: Int,
            vehicle: Vehicle
        )
    }
}