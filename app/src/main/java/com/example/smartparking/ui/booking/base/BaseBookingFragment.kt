package com.example.smartparking.ui.booking.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.smartparking.data.model.ParkingDetailDataStore
import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.ui.booking.BookingSharedViewModel

abstract class BaseBookingFragment : Fragment(), BaseBookingView {
    private val baseBookingViewModel: BaseBookingViewModel by viewModels()

    private val bookingSharedViewModel: BookingSharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBookingViewModel.baseBookingView = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    override fun syncParkingDetailToShareViewModel(parkingDetail: ParkingDetailDataStore?) {
        bookingSharedViewModel.setParkingDetail(parkingDetail)
    }

    override fun syncSelectedVehicleToShareViewModel(vehicle: Vehicle?) {
        bookingSharedViewModel.setSelectedVehicle(vehicle)
    }

    open fun initComponents() {
        bookingSharedViewModel.parkingDetailDataStore.observe(viewLifecycleOwner, Observer {
            baseBookingViewModel.onSyncParkingDetailFromShareViewModel(it)
        })

        bookingSharedViewModel.selectedVehicle.observe(viewLifecycleOwner, Observer {
            baseBookingViewModel.onSyncSelectedVehicleFromShareViewModel(it)
        })
    }
}