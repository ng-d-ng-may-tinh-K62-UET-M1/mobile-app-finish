package com.example.smartparking.ui.myparking

import android.annotation.SuppressLint
import com.example.smartparking.data.model.Booking
import com.example.smartparking.utils.Converter
import com.example.smartparking.R
import com.example.smartparking.utils.TimeUtils

class MyParkingFromBookingConverter : Converter<Booking, MyParkingDescription> {
    override fun convert(f: Booking): MyParkingDescription {
        return object : MyParkingDescription {
            override fun getNumberPlate(): String {
                return f.plate.orEmpty()
            }

            override fun statusName(): String {
                return f.status.orEmpty()
            }

            @SuppressLint("ResourceAsColor")
            override fun getStatusColor(): Int {
                return when (f.status) {
                    BookingStatus.PENDING.value -> {
                        R.color.status_pending
                    }
                    BookingStatus.ACTIVE.value -> {
                        R.color.status_active
                    }
                    BookingStatus.DONE.value -> {
                        R.color.status_done
                    }
                    else -> {
                        R.color.status_outdated
                    }
                }
            }

            override fun getTimeIn(): String {
                return TimeUtils.formatDate(f.timeIn!!.toDate(), TimeUtils.DateFormat.FindParkingFormatDate)
            }

            override fun getTimeOut(): String {
                return TimeUtils.formatDate(f.timeOut!!.toDate(), TimeUtils.DateFormat.FindParkingFormatDate)
            }
        }
    }

}