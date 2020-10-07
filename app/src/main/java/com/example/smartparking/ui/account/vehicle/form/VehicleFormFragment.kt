package com.example.smartparking.ui.account.vehicle.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.smartparking.databinding.VehicleFormFragmentBinding
import com.example.smartparking.utils.extensions.findNavController
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
            viewmodel = viewModel
        }
        viewModel.setVehicleFormView(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.apply {
            edtPlateNumber.setTextChangeListener {
                viewModel.setVehicleName(it)
            }
            edtPlateName.setTextChangeListener {
                viewModel.setVehiclePlate(it)
            }
        }
    }

    override fun goBack() {
        findNavController()?.navigateUp()
    }

}