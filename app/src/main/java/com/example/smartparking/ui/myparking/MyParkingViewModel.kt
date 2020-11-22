package com.example.smartparking.ui.myparking

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.smartparking.data.model.Booking
import com.example.smartparking.data.model.Parking
import com.example.smartparking.repositories.booking.BookingRepository
import com.example.smartparking.repositories.parking.ParkingRepository
import com.example.smartparking.utils.COLLECTION_BOOKING_LIST
import com.example.smartparking.utils.COLLECTION_PARKING_LIST
import com.example.smartparking.utils.auth.AuthenticationManager
import com.google.firebase.firestore.FirebaseFirestore

class MyParkingViewModel @ViewModelInject constructor(
    authenticationManager: AuthenticationManager,
    private val bookingRepository: BookingRepository
) : ViewModel() {
    var myParkingView: MyParkingView? = null

    private val _userUID = MutableLiveData<String>(authenticationManager.getCurrentUserId())

    private val converter =  MyParkingFromBookingConverter()

    val parkingLists = Transformations.switchMap(_userUID) {
        it?.let {id ->
            bookingRepository.getBookingBelongToUserId(id).asLiveData().switchMap {
                it.getLiveDataIfSuccess()
            }
        }
    }

    val parkingListsDataStore = parkingLists.map {
        it?.map { booking -> MyParkingDataStore.createFromBooking(booking, converter) }
    }
}