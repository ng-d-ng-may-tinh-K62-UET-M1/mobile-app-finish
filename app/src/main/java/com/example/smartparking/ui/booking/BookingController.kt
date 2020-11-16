package com.example.smartparking.ui.booking

import com.airbnb.epoxy.TypedEpoxyController
import com.example.smartparking.R
import com.example.smartparking.ui.account.vehicle.list.models.ItemVehicleBooking_
import com.example.smartparking.ui.booking.models.SectionBookingDetail_
import com.example.smartparking.ui.booking.models.SectionTitleModel_

class BookingController : TypedEpoxyController<List<BookingItems>>() {
    var callbacks: BookingCallbacks? = null

    override fun buildModels(data: List<BookingItems>?) {
        data?.mapIndexed { index, bookingItems ->
            when (bookingItems) {
                is BookingItems.BookingParkingDetail -> addBookingParkingDetail(index, bookingItems)
                is BookingItems.BookingVehicle -> addBookingVehicle(index, bookingItems)
            }
        }
    }

    private fun addBookingParkingDetail(index: Int, item: BookingItems.BookingParkingDetail) {
        SectionTitleModel_()
            .id("${item.id}_title")
            .titleRes(R.string.booking_reservation_details)
            .addTo(this)
        SectionBookingDetail_()
            .id(item.id)
            .parkingDetailDataStore(item.parkingDetail)
            .addTo(this)
    }

    private fun addBookingVehicle(index: Int, item: BookingItems.BookingVehicle) {
        SectionTitleModel_()
            .id("${item.id}_title")
            .titleRes(R.string.booking_select_vehicle)
            .canViewMore(true)
            .clickListener { _ ->
                callbacks?.goToSelectVehicle()
            }
            .addTo(this)
        ItemVehicleBooking_()
            .id(item.id)
            .vehicle(item.vehicle)
            .addTo(this)
    }


    interface BookingCallbacks {
        fun goToSelectVehicle()
    }
}