package com.example.smartparking.ui.account.paymentmethod.models

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.smartparking.BR
import com.example.smartparking.R
import com.example.smartparking.data.model.PaymentMethod

@EpoxyModelClass(layout = R.layout.item_payment_method_in_list)
abstract class PaymentMethodInListModel : DataBindingEpoxyModel() {
    @EpoxyAttribute
    var paymentMethod: PaymentMethod? = null

    @EpoxyAttribute
    var clickListener: View.OnClickListener? = null

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.apply {
            setVariable(BR.paymentMethod, paymentMethod)
            setVariable(BR.clickListener, clickListener)
        }
    }
}