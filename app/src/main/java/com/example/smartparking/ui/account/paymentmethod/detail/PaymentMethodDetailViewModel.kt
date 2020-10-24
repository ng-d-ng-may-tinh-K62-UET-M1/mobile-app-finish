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

    fun setData(paymentMethod: PaymentMethod) {
        _paymentMethod.value = paymentMethod
    }

    fun setCartNumberPaymentMethod(value: String) {
        _paymentMethod.value = _paymentMethod.value?.copy(cardNumber = value)
    }

    fun setExpDatePaymentMethod(value: String) {
        _paymentMethod.value = _paymentMethod.value?.copy(expiredDate = value)
    }

    fun setCVVPaymentMethod(value: String) {
        _paymentMethod.value = _paymentMethod.value?.copy(cvv = value)
    }

    fun goBack() {
        paymentMethodDetailView?.goBack()
    }

    fun updatePaymentMethod() {
        _paymentMethod.value?.let {
            paymentMethodRepository.updatePaymentMethod(it)
        }
        goBack()
    }

    fun deletePaymentMethod() {
        _paymentMethod.value?.let {
            paymentMethodRepository.deletePaymentMethod(it)
        }
        goBack()
    }
}