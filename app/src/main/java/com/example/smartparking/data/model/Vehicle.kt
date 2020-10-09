package com.example.smartparking.data.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle(
    @DocumentId
    val documentId: String? = null,
    var uid: String? = null,
    var plateNumber: String? = null,
    var name: String? = null
) : Parcelable

@Parcelize
data class VehicleWithOutDocumentId(
    var uid: String? = null,
    var plateNumber: String? = null,
    var name: String? = null
) : Parcelable