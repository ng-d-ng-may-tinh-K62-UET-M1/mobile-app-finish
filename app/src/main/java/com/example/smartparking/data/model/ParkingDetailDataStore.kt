package com.example.smartparking.data.model

import android.os.Parcelable
import com.example.smartparking.data.request.FindParkingRequest
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParkingDetailDataStore(
    var locationTitle: String? = null,
    var timeIn: String? = null,
    var timeOut: String? = null
) : Parcelable {
    companion object {
        fun createFromFindParkingRequestAndParking(findParkingRequest: FindParkingRequest?, parking: Parking?) : ParkingDetailDataStore {
            return ParkingDetailDataStore(
                locationTitle = parking?.title,
                timeIn = findParkingRequest?.timeIn,
                timeOut = findParkingRequest?.timeOut
            )
        }
    }
}