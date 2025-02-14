package com.example.smartparking.ui.booking

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartparking.data.model.Booking
import com.example.smartparking.data.model.Parking
import com.example.smartparking.data.model.ParkingDetailDataStore
import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.data.request.FindParkingDateTime
import com.example.smartparking.data.request.FindParkingRequest
import com.example.smartparking.repositories.vehicles.VehicleRepository
import com.example.smartparking.ui.booking.base.BaseBookingView
import com.example.smartparking.utils.COLLECTION_BOOKING_LIST
import com.example.smartparking.utils.COLLECTION_VEHICLES
import com.example.smartparking.utils.auth.AuthenticationManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class BookingViewModel @ViewModelInject constructor(
    private val vehicleRepository: VehicleRepository,
    authenticationManager: AuthenticationManager,
    private val firebaseFirestore: FirebaseFirestore
) : ViewModel() {
    private val _userUID = MutableLiveData<String>(authenticationManager.getCurrentUserId())

    private val _selectedVehicle = MutableLiveData<Vehicle>()
    val selectedVehicle: LiveData<Vehicle>
        get() = _selectedVehicle

    var bookingView: BookingView? = null
    var baseBookingView: BaseBookingView? = null

    private var bookingItemsInternal = mutableListOf<BookingItems>()

    private val _bookingItems = MutableLiveData<List<BookingItems>>()
    val bookingItems: LiveData<List<BookingItems>> = _bookingItems

    var findParkingDateTime: FindParkingDateTime? = null


    init {
        initBookingItems()
        _bookingItems.postValue(bookingItemsInternal)
    }

    private fun initBookingItems() {
        bookingItemsInternal.add(BookingItems.BookingParkingDetail(ParkingDetailDataStore()))
        bookingItemsInternal.add(BookingItems.BookingVehicle(Vehicle()))
    }

    fun goBack() {
        bookingView?.goBack()
    }

    fun setData(
        findParkingRequest: FindParkingRequest?,
        parking: Parking?,
        findParkingDateTime: FindParkingDateTime?
    ) {
        this.findParkingDateTime = findParkingDateTime
        syncParkingDetailToShareViewModel(
            ParkingDetailDataStore.createFromFindParkingRequestAndParking(
                findParkingRequest,
                parking
            )
        )
        viewModelScope.launch {
            getSelectedVehicle()
        }
    }

    private fun getSelectedVehicle() {
        _userUID.value?.let {
            firebaseFirestore.collection(COLLECTION_VEHICLES).whereEqualTo("uid", it)
                .get()
                .addOnSuccessListener { documents ->
                    _selectedVehicle.value = documents.toObjects(Vehicle::class.java).firstOrNull()
                    syncSelectedVehicleToShareViewModel(_selectedVehicle.value)
                }
                .addOnFailureListener { exception ->
                    println(exception.message)
                }
        }
    }

    private fun syncParkingDetailToShareViewModel(parkingDetailDataStore: ParkingDetailDataStore?) {
        baseBookingView?.syncParkingDetailToShareViewModel(parkingDetailDataStore)
    }

    private fun syncSelectedVehicleToShareViewModel(vehicle: Vehicle?) {
        baseBookingView?.syncSelectedVehicleToShareViewModel(vehicle)
    }

    fun onSyncParkingDetailFromShareViewModel(parkingDetailDataStore: ParkingDetailDataStore?) {
        bookingItemsInternal.filterIsInstance<BookingItems.BookingParkingDetail>().map {
            if (parkingDetailDataStore != null) it.parkingDetail = parkingDetailDataStore
        }
        _bookingItems.postValue(bookingItemsInternal)
    }

    fun onSyncSelectedVehicleFromShareViewModel(vehicle: Vehicle?) {
        _selectedVehicle.value = vehicle
        bookingItemsInternal.filterIsInstance<BookingItems.BookingVehicle>().map {
            it.vehicle = vehicle
        }
        _bookingItems.postValue(bookingItemsInternal)
    }

    fun booking() {
        _userUID.value?.let {
            val request = Booking.createFrom(
                it, findParkingDateTime, _selectedVehicle.value
            )
            firebaseFirestore.collection(COLLECTION_BOOKING_LIST).add(request)
                .addOnSuccessListener {
                    bookingView?.goToMyParking()
                }
                .addOnFailureListener {
                    throw it
                }
        }
    }
}