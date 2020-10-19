package com.example.smartparking.ui.account.paymentmethod.form

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartparking.data.model.PaymentMethod
import com.example.smartparking.repositories.paymentmethod.PaymentMethodRepository
import com.example.smartparking.utils.auth.AuthenticationManager

class PaymentMethodFormViewModel @ViewModelInject constructor(
    private val paymentMethodRepository: PaymentMethodRepository,
    authenticationManager: AuthenticationManager
) : ViewModel() {
    private val currentUid = authenticationManager.getCurrentUserId()
    var paymentMethodFormView: PaymentMethodFormView? = null

    private val _paymentMethod = MutableLiveData<PaymentMethod>(PaymentMethod())
    val paymentMethod: LiveData<PaymentMethod>
        get() = _paymentMethod

    fun setCartNumberPaymentMethod(value: String) {
        _paymentMethod.value = _paymentMethod.value?.copy(cardNumber = value)
    }

    fun setExpDatePaymentMethod(value: String) {
        _paymentMethod.value = _paymentMethod.value?.copy(expiredDate = value)
    }

    fun setCVVPaymentMethod(value: String) {
        _paymentMethod.value = _paymentMethod.value?.copy(cvv = value)
    }

    fun setView(paymentMethodFormView: PaymentMethodFormView) {
        this.paymentMethodFormView = paymentMethodFormView
    }

    fun addNewPaymentMethod() {
        val paymentMethodRequest = _paymentMethod.value
        paymentMethodRequest?.uid = currentUid
        paymentMethodRequest?.let { paymentMethod ->
            paymentMethodRepository.addPaymentMethod(paymentMethod)
        }
        goBack()
    }

    fun goBack() {
        paymentMethodFormView?.goBack()
    }
}