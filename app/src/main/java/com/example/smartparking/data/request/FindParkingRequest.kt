package com.example.smartparking.data.request

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FindParkingRequest(
    @SerializedName("time_in")
    @Expose
    val timeIn: String? = null,

    @SerializedName("time_out")
    @Expose
    val timeOut: String? = null
) : Parcelable