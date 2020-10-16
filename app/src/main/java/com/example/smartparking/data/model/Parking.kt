package com.example.smartparking.data.model

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Parking(
    @get:PropertyName("title")
    @set:PropertyName("title")
    var title: String = "",

    @get:PropertyName("latitude")
    @set:PropertyName("latitude")
    var latitude: String = "",

    @get:PropertyName("longtitude")
    @set:PropertyName("longtitude")
    var longtitude: String = "",

    @get:PropertyName("slots_num")
    @set:PropertyName("slots_num")
    var slotsNum: String = ""
) : Parcelable