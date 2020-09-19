package com.example.smartparking.splash

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.smartparking.R
import com.example.smartparking.data.preference.PreferenceDataSource
import com.example.smartparking.ui.MainActivity
import com.example.smartparking.ui.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var preferenceDataSource: PreferenceDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (isUserOnboarded()) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, OnboardingActivity::class.java))
        }
    }

    private fun isUserOnboarded(): Boolean = preferenceDataSource.getIsUserOnboarded()
}