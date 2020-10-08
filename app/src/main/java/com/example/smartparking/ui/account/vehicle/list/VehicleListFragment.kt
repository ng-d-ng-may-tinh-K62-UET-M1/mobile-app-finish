package com.example.smartparking.ui.account.vehicle.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.databinding.VehicleListFragmentBinding
import com.example.smartparking.utils.extensions.findNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VehicleListFragment : Fragment(), VehicleListView,
    VehicleListController.VehicleInListCallbacks {
    @Inject
    lateinit var vehicleListController: VehicleListController

    lateinit var binding: VehicleListFragmentBinding

    private val vehicleListViewModel: VehicleListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vehicleListController.callbacks = this
        binding = VehicleListFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            viewmodel = vehicleListViewModel
            lifecycleOwner = viewLifecycleOwner
            vehicleItems.setController(vehicleListController)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vehicleListViewModel.setView(this)
        listenViewModel()
    }

    private fun listenViewModel() {
        vehicleListViewModel.vehicles.observe(viewLifecycleOwner, Observer {
            vehicleListController.setData(it.data)
        })
    }

    override fun goBack() {
        findNavController()?.navigateUp()
    }

    override fun goToVehicleForm() {
        findNavController()?.navigate(
            VehicleListFragmentDirections.actionVehicleListFragmentToVehicleFormFragment()
        )
    }

    override fun goToVehicleDetail(vehicle: Vehicle) {
        findNavController()?.navigate(
            VehicleListFragmentDirections.actionVehicleListFragmentToVehicleDetailFragment(vehicle)
        )
    }

    override fun onClickVehicleInList(index: Int, vehicle: Vehicle) {
        goToVehicleDetail(vehicle)
    }

}