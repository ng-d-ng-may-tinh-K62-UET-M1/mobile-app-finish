package com.example.smartparking.ui.account.vehicle.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.smartparking.data.model.Vehicle
import com.example.smartparking.databinding.VehicleListFragmentBinding
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
        listenViewModel()
        binding.apply {
            vehicleItems.setController(vehicleListController)
            viewmodel = vehicleListViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onDestroyView() {
        vehicleListController.callbacks = null
        binding.vehicleItems.clear()
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vehicleListViewModel.setView(this)
    }

    private fun listenViewModel() {
        vehicleListViewModel.vehicles.observe(viewLifecycleOwner, Observer {
            vehicleListController.setData(it)
        })
    }

    override fun goBack() {
        findNavController().navigateUp()
    }

    override fun goToVehicleForm() {
        findNavController().navigate(
            VehicleListFragmentDirections.actionVehicleListFragmentToVehicleFormFragment()
        )
    }

    override fun goToVehicleDetail(vehicle: Vehicle) {
        findNavController().navigate(
            VehicleListFragmentDirections.actionVehicleListFragmentToVehicleDetailFragment(vehicle)
        )
    }

    override fun onClickVehicleInList(index: Int, vehicle: Vehicle) {
        goToVehicleDetail(vehicle)
    }

}