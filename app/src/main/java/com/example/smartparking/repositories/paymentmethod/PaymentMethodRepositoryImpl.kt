package com.example.smartparking.repositories.paymentmethod

import com.example.smartparking.data.model.PaymentMethod
import com.example.smartparking.utils.COLLECTION_PAYMENT_METHOD
import com.example.smartparking.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class PaymentMethodRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : PaymentMethodRepository {

    @ExperimentalCoroutinesApi
    override fun getPaymentMethods(uid: String): Flow<Resource<List<PaymentMethod>>> =
        callbackFlow {
            offer(Resource.loading())
            val listener =
                firebaseFirestore.collection(COLLECTION_PAYMENT_METHOD).whereEqualTo("uid", uid)
                    .addSnapshotListener { value, error ->
                        if (value != null) {
                            offer(Resource.success(value.toObjects(PaymentMethod::class.java)))
                        }
                        error?.let {
                            offer(Resource.error(it.message.toString()))
                            cancel(it.message.toString())
                        }
                    }
            awaitClose {
                listener.remove()
                cancel()
            }
        }

    override fun addPaymentMethod(paymentMethod: PaymentMethod) {
        firebaseFirestore.collection(COLLECTION_PAYMENT_METHOD).add(paymentMethod)
            .addOnSuccessListener { println("add vehicle success") }
            .addOnFailureListener { e -> throw e }
    }

    override fun updatePaymentMethod(paymentMethod: PaymentMethod) {
        paymentMethod.documentId?.let {
            val paymentMethodToMap = paymentMethodToMap(paymentMethod)
            firebaseFirestore.collection(COLLECTION_PAYMENT_METHOD).document(it)
                .update(paymentMethodToMap)
                .addOnSuccessListener { println("update document success") }
                .addOnFailureListener { e -> throw e }
        }
    }

    override fun deletePaymentMethod(paymentMethod: PaymentMethod) {
        paymentMethod.documentId?.let {
            firebaseFirestore.collection(COLLECTION_PAYMENT_METHOD).document(it).delete()
        }
    }


    private fun paymentMethodToMap(paymentMethod: PaymentMethod): Map<String, Any?> {
        return mapOf(
            PaymentMethod.CARD_NUMBER_FIELD to paymentMethod.cardNumber,
            PaymentMethod.EXPIRED_DATE_FIELD to paymentMethod.expiredDate,
            PaymentMethod.CVV_FIELD to paymentMethod.cvv
        )
    }
}