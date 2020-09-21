package com.example.smartparking.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.smartparking.databinding.HomeFragmentBinding
import com.example.smartparking.splash.SplashActivity
import com.example.smartparking.ui.onboarding.OnboardingActivity
import com.example.smartparking.utils.auth.AuthenticationManager
import com.example.smartparking.utils.livedata.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: HomeFragmentBinding

    @Inject
    lateinit var authenticationManager: AuthenticationManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.test.text = authenticationManager.getCurrentUserName()
        binding.test2.text = authenticationManager.getCurrentUserEmail()
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
}