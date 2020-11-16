package com.example.smartparking.ui.account.vehicle.list.models

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.smartparking.R
import com.example.smartparking.BR
import com.example.smartparking.data.model.Vehicle

@EpoxyModelClass(layout = R.layout.item_vehicle_booking)
abstract class ItemVehicleBooking : DataBindingEpoxyModel() {
    @EpoxyAttribute
    var vehicle: Vehicle? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.apply {
            setVariable(BR.vehicle, vehicle)
            setVariable(BR.clickListener, clickListener)
        }
    }

    override fun unbind(holder: DataBindingHolder) {
        holder.dataBinding.setVariable(BR.clickListener, null)
        super.unbind(holder)
    }
}