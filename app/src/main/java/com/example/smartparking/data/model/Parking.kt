package com.example.smartparking.data.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Parking(
    @SerializedName("document_id")
    var documentId: String? = null,

    @SerializedName("title")
    var title: String = "",

    @SerializedName("latitude")
    var latitude: String = "",

    @SerializedName("longitude")
    var longitude: String = "",

    @SerializedName("slots_num")
    var slotsNum: String = ""
) : Parcelable