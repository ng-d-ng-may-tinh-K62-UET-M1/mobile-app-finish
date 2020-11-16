package com.example.smartparking.ui.booking.vehicles

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.smartparking.R
import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.databinding.BookingVehicleFragmentBinding
import com.example.smartparking.ui.booking.base.BaseBookingFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BookingVehicleFragment : BaseBookingFragment(), BookingVehicleController.BookingVehiclesCallBacks, BookingVehicleListView {
    @Inject
    lateinit var bookingVehicleController: BookingVehicleController

    lateinit var binding: BookingVehicleFragmentBinding

    private val bookingVehicleViewModel: BookingVehicleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookingVehicleController.callbacks = this
        binding = BookingVehicleFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            vehicleItems.setController(bookingVehicleController)
            lifecycleOwner = viewLifecycleOwner
            viewmodel = bookingVehicleViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bookingVehicleViewModel.setView(this)
        listenViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun listenViewModel() {
        bookingVehicleViewModel.vehicles.observe(viewLifecycleOwner, Observer {
            bookingVehicleController.setData(it)
        })
    }

    override fun onDestroyView() {
        bookingVehicleController.callbacks = null
        binding.vehicleItems.clear()
        super.onDestroyView()
    }

    override fun onClickVehicleInList(index: Int, vehicle: Vehicle) {
        bookingSharedViewModel.setSelectedVehicle(vehicle)
        findNavController().navigateUp()
    }

    override fun goBack() {
        findNavController().navigateUp()
    }

}