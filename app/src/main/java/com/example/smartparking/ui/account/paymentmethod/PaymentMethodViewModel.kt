package com.example.smartparking.ui.account.paymentmethod

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.smartparking.repositories.paymentmethod.PaymentMethodRepository
import com.example.smartparking.utils.COLLECTION_PAYMENT_METHOD
import com.example.smartparking.utils.auth.AuthenticationManager
import com.google.firebase.firestore.FirebaseFirestore

class PaymentMethodViewModel @ViewModelInject constructor(
    private val paymentMethodRepository: PaymentMethodRepository,
    authenticationManager: AuthenticationManager
) : ViewModel() {
    private val _userUID = MutableLiveData<String>(authenticationManager.getCurrentUserId())
    val paymentMethods = Transformations.switchMap(_userUID) {
        it?.let { uid ->
            paymentMethodRepository.getPaymentMethods(uid).asLiveData().switchMap { list ->
                list.getLiveDataIfSuccess()
            }
        }
    }
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