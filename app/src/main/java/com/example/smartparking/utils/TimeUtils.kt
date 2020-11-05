package com.example.smartparking.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {
    enum class DateFormat(val value: String) {
        BasicFormatDate("dd/MM/yyyy"),
        OmFormatDate("dd/MM/yyyy - HH:mm"),
        OmDetailFormatDate("HH:mm, EEEE dd/MM/yyyy"),
        OmOrderListFormatDate("yyyy-MM-dd'T'HH:mm:ss"),
        PlFormatDate("yyyy-MM-dd'T'HH:mm:ss"),
        FullDateTimeWithTimezone("yyyy-MM-dd'T'HH:mm:ssZZZZZ"),
        DisplayFormatDate("dd/MM/yyyy - hh:mm a"),
        MonthOnlyFormatDate("MMMM"),
        PvisFormatDate("yyyy-MM-dd"),
        PlFormatTimeRange("HH:mm:ss"),
        PlFormatTimeRangeWithoutSecond("HH:mm"),
        EndShiftFormatDate("HH:mm - dd/MM/yyyy"),
        FindParkingFormatDate("EEEE, MMM dd, HH:mma"),
        FindParkingFormatDateRequest("yyyy-MM-dd HH:mm:ss")
    }

    @JvmStatic
    fun formatDate(source: Date, dateFormat: DateFormat): String {
        return SimpleDateFormat(dateFormat.value, Locale.getDefault()).format(source)
    }
}