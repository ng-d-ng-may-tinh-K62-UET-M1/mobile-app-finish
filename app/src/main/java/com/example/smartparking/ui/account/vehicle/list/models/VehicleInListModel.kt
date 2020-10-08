package com.example.smartparking.ui.account.vehicle.list.models

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.smartparking.BR
import com.example.smartparking.R
import com.example.smartparking.data.model.Vehicle

@EpoxyModelClass(layout = R.layout.item_vehicle_in_list)
abstract class VehicleInListModel : DataBindingEpoxyModel() {
    @EpoxyAttribute
    lateinit var vehicle: Vehicle

    @EpoxyAttribute
    var clickListener: View.OnClickListener? = null

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.let {
            it.setVariable(BR.vehicle, vehicle)
            it.setVariable(BR.clickListener, clickListener)
        }
    }
}