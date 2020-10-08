package com.example.smartparking.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle(
    var uid: String? = null,
    var plateNumber: String? = null,
    var name: String? = null
) : Parcelable