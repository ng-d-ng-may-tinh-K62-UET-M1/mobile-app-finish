package com.example.smartparking.ui.account.vehicle.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.smartparking.databinding.VehicleFormFragmentBinding
import com.example.smartparking.utils.extensions.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleFormFragment : Fragment(), VehicleFormView {

    private val viewModel: VehicleFormViewModel by viewModels()

    lateinit var binding: VehicleFormFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleFormFragmentBinding.inflate(layoutInflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }
        viewModel.setVehicleFormView(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.edtPlateNumber.setTextChangeListener { text ->
            viewModel.setVehiclePlate(text)
        }
        binding.edtPlateName.setTextChangeListener { text ->
            viewModel.setVehicleName(text)
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