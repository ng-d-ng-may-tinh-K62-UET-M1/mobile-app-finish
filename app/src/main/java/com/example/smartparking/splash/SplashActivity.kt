package com.example.smartparking.splash

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.smartparking.R
import com.example.smartparking.data.preference.PreferenceDataSource
import com.example.smartparking.ui.MainActivity
import com.example.smartparking.ui.onboarding.OnboardingActivity
import com.example.smartparking.utils.checkAllMatched
import com.example.smartparking.utils.livedata.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel.lanchDestination.observe(this, EventObserver { destination ->
            when (destination) {
                SplashDestination.MAIN_ACTIVITY -> startActivity(Intent(this, MainActivity::class.java))
                SplashDestination.ONBOARDING -> startActivity(Intent(this, OnboardingActivity::class.java))
            }.checkAllMatched
            finish()
        })
    }

}
