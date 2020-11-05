package com.example.smartparking.ui.selecttime

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartparking.data.request.FindParkingRequest
import com.example.smartparking.repositories.datasource.FindParkingDataSource
import com.example.smartparking.utils.TimeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class SelectTimeViewModel @ViewModelInject constructor(
    private val findParkingDataSource: FindParkingDataSource
) : ViewModel() {
    var endDate: Calendar = Calendar.getInstance()
    var startDate: Calendar = run {
        val startCalendar = Calendar.getInstance()
        setTimeToStartOfDay(startCalendar)
        startCalendar
    }

    val startDateLabel: LiveData<String> = MutableLiveData(formatCalendar(startDate))
    val endDateLabel: LiveData<String> = MutableLiveData(formatCalendar(endDate))

    fun updateStartDate(date: Calendar) {
        startDate = date
        (startDateLabel as? MutableLiveData)?.value = formatCalendar(date)
    }

    fun updateEndDate(date: Calendar) {
        endDate = date
        (endDateLabel as? MutableLiveData)?.value = formatCalendar(date)
    }

    private fun formatCalendar(date: Calendar): String {
        return TimeUtils.formatDate(date.time, TimeUtils.DateFormat.FindParkingFormatDate)
    }

    private fun formatCalenderRequest(date: Calendar): String {
        return TimeUtils.formatDate(date.time, TimeUtils.DateFormat.FindParkingFormatDateRequest)
    }

    fun findParking() {
        viewModelScope.launch(Dispatchers.IO) {
            val a =findParkingDataSource.getParkingListAvailable(
                request = FindParkingRequest(
                    timeIn = formatCalenderRequest(startDate),
                    timeOut = formatCalenderRequest(endDate)
                )
            )
        }
    }

    private suspend fun findParkingFromRemote() {

    }

    private fun setTimeToStartOfDay(date: Calendar) {
        date[Calendar.HOUR_OF_DAY] = 0
        date[Calendar.MINUTE] = 0
        date[Calendar.SECOND] = 0
    }
}