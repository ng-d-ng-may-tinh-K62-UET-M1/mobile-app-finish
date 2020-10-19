package com.example.smartparking.ui.account.paymentmethod

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.example.smartparking.data.model.PaymentMethod
import com.example.smartparking.ui.account.paymentmethod.models.PaymentMethodInListModel_

class PaymentMethodController : TypedEpoxyController<List<PaymentMethod>>() {
    var callback: PaymentMethodListCallback? = null

    override fun buildModels(data: List<PaymentMethod>?) {
        data?.mapIndexed { index, paymentMethod ->
            PaymentMethodInListModel_()
                .id("payment_method_in_list_$index")
                .paymentMethod(paymentMethod)
                .clickListener(View.OnClickListener {
                    callback?.onClickPaymentMethod(index, paymentMethod)
                })
                .addTo(this)
        }
    }

    interface PaymentMethodListCallback {
        fun onClickPaymentMethod(
            index: Int,
            paymentMethod: PaymentMethod
        )
    }

}