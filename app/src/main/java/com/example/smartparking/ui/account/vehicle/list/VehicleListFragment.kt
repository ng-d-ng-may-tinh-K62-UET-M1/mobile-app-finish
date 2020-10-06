package com.example.smartparking.ui.account.vehicle.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.smartparking.databinding.VehicleListFragmentBinding
import com.example.smartparking.utils.extensions.findNavController

class VehicleListFragment : Fragment(), VehicleListView {
    lateinit var binding: VehicleListFragmentBinding

    private val viewModel: VehicleListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleListFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setView(this)
    }

    override fun goBack() {
        findNavController()?.navigateUp()
    }

    override fun goToVehicleForm() {
        findNavController()?.navigate(
            VehicleListFragmentDirections.actionVehicleListFragmentToVehicleFormFragment()
        )
    }

}