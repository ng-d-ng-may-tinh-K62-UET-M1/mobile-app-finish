package com.example.smartparking.ui.myparking.models

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.smartparking.R
import com.example.smartparking.BR
import com.example.smartparking.ui.myparking.MyParkingDataStore

@EpoxyModelClass(layout = R.layout.item_parking_in_list_my_parking)
abstract class ParkingInListMyParkingModel : DataBindingEpoxyModel() {
    @EpoxyAttribute
    var dataStore: MyParkingDataStore? = null

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.apply {
            setVariable(BR.datastore, dataStore)
        }
    }
}