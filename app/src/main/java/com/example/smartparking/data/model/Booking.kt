package com.example.smartparking.data.model

import android.os.Parcelable
import com.example.smartparking.data.request.FindParkingDateTime
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Booking(
    @DocumentId
    var documentId: String? = null,

    @get:PropertyName("uid")
    @set:PropertyName("uid")
    var uid: String? = null,

    @get:PropertyName("plate")
    @set:PropertyName("plate")
    var plate: String? = null,

    @get:PropertyName("status")
    @set:PropertyName("status")
    var status: String? = null,

    @get:PropertyName("vehicle_id")
    @set:PropertyName("vehicle_id")
    var vehicleId: String? = null,

    @get:PropertyName("time_in")
    @set:PropertyName("time_in")
    var timeIn: Timestamp? = null,

    @get:PropertyName("time_out")
    @set:PropertyName("time_out")
    var timeOut: Timestamp? = null

) : Parcelable {
    companion object {
        fun createFrom(uid: String, findParkingDateTime: FindParkingDateTime?, vehicle: Vehicle?) : Booking {
            return Booking(
                uid = uid,
                plate = vehicle?.plateNumber,
                status = "pending",
                timeIn = Timestamp(findParkingDateTime?.timeIn ?: Date()),
                timeOut = Timestamp(findParkingDateTime?.timeOut ?: Date()),
                vehicleId = vehicle?.documentId
            )
        }
    }
}
