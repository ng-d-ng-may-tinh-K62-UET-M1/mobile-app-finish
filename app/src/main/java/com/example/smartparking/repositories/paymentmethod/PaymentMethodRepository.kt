package com.example.smartparking.repositories.paymentmethod

import com.example.smartparking.data.model.PaymentMethod
import com.example.smartparking.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PaymentMethodRepository {
    fun getPaymentMethods(uid: String) : Flow<Resource<List<PaymentMethod>>>
    fun addPaymentMethod(paymentMethod: PaymentMethod)
    fun updatePaymentMethod(paymentMethod: PaymentMethod)
    fun deletePaymentMethod(paymentMethod: PaymentMethod)
}