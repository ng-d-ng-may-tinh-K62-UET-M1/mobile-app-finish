package com.example.smartparking.ui.booking.models

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.smartparking.R
import com.example.smartparking.BR
import com.example.smartparking.data.model.ParkingDetailDataStore

@EpoxyModelClass(layout = R.layout.section_booking_detail)
abstract class SectionBookingDetail : DataBindingEpoxyModel() {
    @EpoxyAttribute
    var parkingDetailDataStore: ParkingDetailDataStore? = null

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.apply {
            setVariable(BR.dataStore, parkingDetailDataStore)
        }
    }
}