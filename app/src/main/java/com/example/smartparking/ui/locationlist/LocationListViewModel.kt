package com.example.smartparking.ui.locationlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.smartparking.data.model.Parking
import com.example.smartparking.data.request.FindParkingDateTime
import com.example.smartparking.data.request.FindParkingRequest
import com.example.smartparking.repositories.findparking.FindParkingService
import com.example.smartparking.repositories.parking.ParkingRepository
import kotlinx.coroutines.*
import java.lang.Exception
import java.time.format.DateTimeFormatter

class LocationListViewModel @ViewModelInject constructor(
    private val findParkingService: FindParkingService
) : ViewModel() {
    private val _listLocationId = MutableLiveData<List<String>>()
    val listLocationId: LiveData<List<String>>
        get() = _listLocationId

    private val _locationList = MutableLiveData<List<Parking>>()
    val locationList: LiveData<List<Parking>>
        get() = _locationList

    var findParkingRequest: FindParkingRequest? = null

    var findParkingDateTime: FindParkingDateTime? = null

    var locationListView: LocationListView? = null

    fun setData(findParkingRequest: FindParkingRequest?, findParkingDateTime: FindParkingDateTime?) {
        this.findParkingRequest = findParkingRequest
        this.findParkingDateTime = findParkingDateTime
        viewModelScope.launch {
            getParkingData(findParkingRequest)
        }
    }

    fun goBack() {
        locationListView?.goBack()
    }

    fun getSelectedParking(title: String) : Parking? {
        return _locationList.value?.findLast {
            it.title == title
        }
    }

    private suspend fun getParkingData(findParkingRequest: FindParkingRequest?) {
        findParkingRequest?.let {
            val response = findParkingService.getParkingListAvailable(it)
            if (response.isSuccessful) {
                _locationList.value = response.body()?.parkingList
            }
        }
    }

}