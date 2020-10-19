package com.example.smartparking.ui.account.paymentmethod.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartparking.data.model.PaymentMethod
import com.example.smartparking.repositories.paymentmethod.PaymentMethodRepository


class PaymentMethodDetailViewModel @ViewModelInject constructor(
    private val paymentMethodRepository: PaymentMethodRepository
) : ViewModel() {
    private val _paymentMethod = MutableLiveData<PaymentMethod>(PaymentMethod())
    val paymentMethod: LiveData<PaymentMethod>
        get() = _paymentMethod

    var paymentMethodDetailView: PaymentMethodDetailView? = null

    fun setView(paymentMethodDetailView: PaymentMethodDetailView) {
        this.paymentMethodDetailView = paymentMethodDetailView
    }

    fun goBack() {
        paymentMethodDetailView?.goBack()
    }
}