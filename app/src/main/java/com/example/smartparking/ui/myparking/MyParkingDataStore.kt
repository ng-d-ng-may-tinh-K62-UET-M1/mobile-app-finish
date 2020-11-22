package com.example.smartparking.ui.myparking

import androidx.annotation.ColorInt
import com.example.smartparking.data.model.Booking
import com.example.smartparking.utils.Converter

data class MyParkingDataStore(
    val plateNumber: String,
    val statusName: String,
    @ColorInt val statusColor: Int,
    val timeIn: String,
    val timeOut: String
) {
    companion object {
        @JvmStatic
        fun createFromBooking(
            booking: Booking,
            converter: Converter<Booking, MyParkingDescription>
        ) : MyParkingDataStore {
            val dataConverter: MyParkingDescription = converter.convert(booking)
            return MyParkingDataStore(
                plateNumber = dataConverter.getNumberPlate(),
                statusName = dataConverter.statusName(),
                statusColor = dataConverter.getStatusColor(),
                timeIn = dataConverter.getTimeIn(),
                timeOut = dataConverter.getTimeOut()
            )
        }
    }
}