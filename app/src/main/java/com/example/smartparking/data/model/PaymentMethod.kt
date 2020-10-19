package com.example.smartparking.data.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentMethod(
    @DocumentId
    val documentId: String? = null,

    @get:PropertyName("card_number")
    @set:PropertyName("card_number")
    var cardNumber: String? = null,

    @get:PropertyName("expried_date")
    @set:PropertyName("expried_date")
    var expiredDate: String? = null,

    @get:PropertyName("cvv")
    @set:PropertyName("cvv")
    var cvv: String? = null,

    @get:PropertyName("uid")
    @set:PropertyName("uid")
    var uid: String? = null
) : Parcelable {
    companion object {
        const val CARD_NUMBER_FIELD = "card_number"
        const val EXPIRED_DATE_FIELD = "expired_date"
        const val CVV_FIELD = "cvv"
        const val UID_FIELD = "uid"
    }
}