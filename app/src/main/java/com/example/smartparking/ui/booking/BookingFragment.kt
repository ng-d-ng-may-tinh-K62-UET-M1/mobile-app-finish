package com.example.smartparking.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.smartparking.databinding.BookingFragmentBinding
import com.example.smartparking.ui.booking.base.BaseBookingFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BookingFragment : BaseBookingFragment(), BookingView, BookingController.BookingCallbacks {
    private lateinit var binding: BookingFragmentBinding

    private val bookingViewModel: BookingViewModel by viewModels()

    @Inject
    lateinit var bookingController: BookingController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookingSharedViewModel.reset()
        arguments?.let { BookingFragmentArgs.fromBundle(it) }?.let {
            bookingViewModel.setData(it.findParkingRequest, it.parking)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookingController.callbacks = this
        binding = BookingFragmentBinding.inflate(layoutInflater, container, false)
        binding.apply {
            items.setController(bookingController)
            viewmodel = bookingViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onDestroyView() {
        binding.items.clear()
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookingViewModel.bookingView = this
        listenViewModel()
    }

    private fun listenViewModel() {
        bookingViewModel.bookingItems.observe(viewLifecycleOwner, Observer {
            bookingController.setData(it)
        })
    }

    override fun goBack() {
        findNavController().navigateUp()
    }

    override fun goToSelectVehicle() {
        findNavController().navigate(BookingFragmentDirections.actionBookingFragmentToBookingVehicleFragment())
    }
}