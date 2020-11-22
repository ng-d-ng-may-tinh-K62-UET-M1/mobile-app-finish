package com.example.smartparking.ui.myparking

import com.airbnb.epoxy.TypedEpoxyController
import com.example.smartparking.R
import com.example.smartparking.BR
import com.example.smartparking.ui.booking.models.SectionTitleModel_
import com.example.smartparking.ui.myparking.models.ParkingInListMyParkingModel_

class MyParkingController : TypedEpoxyController<List<MyParkingDataStore>>() {
    override fun buildModels(data: List<MyParkingDataStore>?) {
        SectionTitleModel_()
            .id("title_")
            .titleRes(R.string.parking_my_reservation_schedule)
            .addTo(this)

        data?.mapIndexed { index, myParkingDataStore ->
            ParkingInListMyParkingModel_()
                .id("${myParkingDataStore.plateNumber}_$index")
                .dataStore(myParkingDataStore)
                .addTo(this)
        }
    }

}