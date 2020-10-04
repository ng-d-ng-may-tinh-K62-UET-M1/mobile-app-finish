package com.example.smartparking.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.smartparking.R
import com.example.smartparking.databinding.ActivityMainBinding
import com.example.smartparking.utils.auth.AuthenticationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
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

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.accountFragment -> showBottomNavigation()
                R.id.myParkingFragment -> showBottomNavigation()
                R.id.homeFragment -> showBottomNavigation()
                else -> hideBottomNavigation()
            }
        }
    }

    private fun hideBottomNavigation() {
        with(bottom_navigation) {
            if (visibility == View.VISIBLE && alpha == 1f) {
                animate()
                    .alpha(0f)
                    .withEndAction {visibility = View.GONE}
            }
        }
    }

    private fun showBottomNavigation() {
        with(bottom_navigation) {
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
        }
    }
}