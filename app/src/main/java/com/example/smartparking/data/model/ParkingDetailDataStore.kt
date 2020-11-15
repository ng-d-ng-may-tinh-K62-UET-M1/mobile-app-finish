package com.example.smartparking.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParkingDetailDataStore(
    var locationTitle: String? = null,
    var timeIn: String? = null,
    var timeOut: String? = null
) : Parcelable