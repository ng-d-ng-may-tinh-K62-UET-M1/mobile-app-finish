package com.example.smartparking.data.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FindParkingResponse(
    @SerializedName("parking_list")
    @Expose
    val parkingList: List<String>? =  null
) : Parcelable