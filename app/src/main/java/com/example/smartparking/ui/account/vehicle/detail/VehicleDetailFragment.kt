package com.example.smartparking.ui.account.vehicle.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartparking.R

class VehicleDetailFragment : Fragment() {

    companion object {
        fun newInstance() = VehicleDetailFragment()
    }

    private lateinit var viewModel: VehicleDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.vehicle_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(VehicleDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}