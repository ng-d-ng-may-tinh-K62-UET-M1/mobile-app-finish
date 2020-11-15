package com.example.smartparking.ui.account.vehicle.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.smartparking.databinding.VehicleDetailFragmentBinding
import com.example.smartparking.utils.extensions.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleDetailFragment : Fragment(), VehicleDetailView {
    private lateinit var binding: VehicleDetailFragmentBinding

    private val vehicleDetailViewModel: VehicleDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            VehicleDetailFragmentArgs.fromBundle(it).let {
                vehicleDetailViewModel.setData(it.vehicle)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleDetailFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            viewmodel = vehicleDetailViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vehicleDetailViewModel.setVehicleDetailView(this)
        binding.apply {
            edtPlateNumber.setTextChangeListener { text ->
                vehicleDetailViewModel.setVehiclePlateNumber(text)
            }

            edtPlateName.setTextChangeListener { text ->
                vehicleDetailViewModel.setVehicleName(text)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    override fun goBack() {
        findNavController().navigateUp()
    }

}