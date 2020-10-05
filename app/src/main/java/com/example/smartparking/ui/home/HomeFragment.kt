package com.example.smartparking.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.smartparking.R
import com.example.smartparking.databinding.HomeFragmentBinding
import com.example.smartparking.ui.onboarding.OnboardingActivity
import com.example.smartparking.utils.auth.AuthenticationManager
import com.example.smartparking.utils.livedata.EventObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: HomeFragmentBinding

    private lateinit var mMap: GoogleMap

    @Inject
    lateinit var authenticationManager: AuthenticationManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listenViewModel()
    }

    private fun listenViewModel() {
        viewModel.signOutRequest.observe(viewLifecycleOwner, EventObserver { request ->
            if (request) startActivity(Intent(activity, OnboardingActivity::class.java))
        })
    }

    companion object {
        const val TAG = "HOMEFRAGMENT"
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            mMap = googleMap
            mMap.setOnMarkerClickListener{marker -> onMarkerClick(marker)}
            val sydney = LatLng(21.038316, 105.782724)
            mMap.addMarker(
                MarkerOptions()
                    .position(sydney)
                    .title("Marker in Sydney")
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show()
        println(p0?.title)
        return true
    }
}