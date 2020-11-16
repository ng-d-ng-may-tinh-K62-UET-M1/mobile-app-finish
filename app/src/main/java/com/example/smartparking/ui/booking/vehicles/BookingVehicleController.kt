package com.example.smartparking.ui.booking.vehicles

import com.airbnb.epoxy.TypedEpoxyController
import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.ui.account.vehicle.list.models.ItemVehicleBooking_

class BookingVehicleController : TypedEpoxyController<List<Vehicle>>() {
    override fun buildModels(data: List<Vehicle>?) {
        data?.mapIndexed { index, vehicle ->
            ItemVehicleBooking_()
                .id(vehicle.documentId)
                .vehicle(vehicle)
                .clickListener { _ ->
                    println("fadfsf")
                    println(vehicle)
                    callbacks?.onClickVehicleInList(index, vehicle)
                }
                .addTo(this)
        }
    }

    var callbacks: BookingVehiclesCallBacks? = null

    interface BookingVehiclesCallBacks {
        fun onClickVehicleInList(index: Int, vehicle: Vehicle)
    }
}