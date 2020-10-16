package com.example.smartparking.data.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle(
    @DocumentId
    val documentId: String? = null,

    @get:PropertyName("uid")
    @set:PropertyName("uid")
    var uid: String? = null,

    @get:PropertyName("plate_number")
    @set:PropertyName("plate_number")
    var plateNumber: String? = null,

    @get:PropertyName("name")
    @set:PropertyName("name")
    var name: String? = null

) : Parcelable {
    companion object {
        const val UID_FIELD = "uid"
        const val  PLATE_NUMBER_FIELD = "plate_number"
        const val NAME_FIELD = "name"
    }
}
