package com.example.smartparking.ui.locationlist

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.smartparking.R
import com.example.smartparking.databinding.LocationListFragmentBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationListFragment : Fragment(), OnMapReadyCallback, LocationListView {
    private val REQUEST_LOCATION_PERMISSION = 1

    private val viewModel: LocationListViewModel by viewModels()

    private lateinit var binding: LocationListFragmentBinding

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { LocationListFragmentArgs.fromBundle(it) }?.let {
            viewModel.setData(it.findParkingRequest, it.findParkingDateTime)
        }
        viewModel.locationListView = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LocationListFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        val mMapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mMapFragment.getMapAsync(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenViewModel()
    }

    private fun listenViewModel() {
        viewModel.locationList.observe(viewLifecycleOwner, Observer {
            it.map { park ->
                val pos = LatLng(park.latitude.toDouble(), park.longitude.toDouble())
                mMap.addMarker(
                    MarkerOptions()
                        .position(pos)
                        .title(park.title)
                )
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMarkerClickListener {
            findNavController().navigate(LocationListFragmentDirections.actionLocationListFragmentToBookingFragment(
                viewModel.findParkingRequest, viewModel.getSelectedParking(it.title), viewModel.findParkingDateTime))
            false
        }
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15f))
        enabledMyLocation()
    }

    private fun isPermissionGranted() : Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }


    private fun enabledMyLocation() {
        if (isPermissionGranted()) {
            mMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enabledMyLocation()
            }
        }
    }

    override fun goBack() {
        findNavController().navigateUp()
    }


}