package com.example.smartparking.ui.account.paymentmethod

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class PaymentMethodViewModel @ViewModelInject constructor(
) : ViewModel() {
    var paymentMethodView: PaymentMethodView? = null

    fun setView(paymentMethodView: PaymentMethodView) {
        this.paymentMethodView = paymentMethodView
    }

    fun goBack() {
        paymentMethodView?.goBack()
    }

    fun goToPaymentMethodForm() {
        paymentMethodView?.goToPaymentMethodForm()
    }
}