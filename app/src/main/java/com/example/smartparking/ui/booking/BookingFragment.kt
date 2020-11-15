package com.example.smartparking.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.smartparking.databinding.BookingFragmentBinding
import com.example.smartparking.ui.booking.base.BaseBookingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingFragment : BaseBookingFragment(), BookingView {
    private lateinit var binding: BookingFragmentBinding

    private val bookingViewModel: BookingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BookingFragmentBinding.inflate(layoutInflater, container, false)
        binding.apply {
            viewmodel = bookingViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookingViewModel.bookingView = this
    }


}