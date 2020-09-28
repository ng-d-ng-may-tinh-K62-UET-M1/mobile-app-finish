package com.example.smartparking.ui.account

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.smartparking.data.model.User
import com.example.smartparking.data.preference.PreferenceDataSource
import com.example.smartparking.databinding.AccountFragmentBinding
import com.example.smartparking.splash.SplashActivity
import com.example.smartparking.utils.Resource
import com.example.smartparking.utils.livedata.EventObserver
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : Fragment() {
    private val viewModel: AccountViewModel by viewModels()

    private lateinit var binding: AccountFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AccountFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            accountViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenViewModel()
    }

    private fun listenViewModel() {
        viewModel.signOutEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) startActivity(Intent(activity, SplashActivity::class.java))
        })
    }
}