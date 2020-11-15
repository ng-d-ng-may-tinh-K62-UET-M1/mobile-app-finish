package com.example.smartparking.ui.booking

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartparking.data.model.ParkingDetailDataStore
import com.example.smartparking.data.model.Vehicle

class BookingViewModel @ViewModelInject constructor() : ViewModel() {
    var bookingView: BookingView? = null
    private var bookingItemsInternal = mutableListOf<BookingItems>()

    private val _bookingItems = MutableLiveData<List<BookingItems>>()
    val bookingItems: LiveData<List<BookingItems>> = _bookingItems

    init {
        initBookingItems()
        _bookingItems.postValue(bookingItemsInternal)
    }

    private fun initBookingItems() {
        bookingItemsInternal.add(BookingItems.BookingParkingDetail(ParkingDetailDataStore()))
        bookingItemsInternal.add(BookingItems.BookingVehicle(Vehicle()))
    }
}