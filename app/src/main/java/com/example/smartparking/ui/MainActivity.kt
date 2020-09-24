package com.example.smartparking.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.smartparking.R
import com.example.smartparking.databinding.ActivityMainBinding
import com.example.smartparking.utils.auth.AuthenticationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var authenticationManager: AuthenticationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        println("authentication Manager ${authenticationManager.getCurrentUserId()}")
        navController = findNavController(R.id.fragment_navigation_host)
        binding.bottomNavigation.setupWithNavController(navController)
    }
}